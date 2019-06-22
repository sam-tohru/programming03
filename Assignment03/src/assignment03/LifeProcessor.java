/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment03;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author lewi0146
 */
public class LifeProcessor {

    public enum ComputeMode {
        JAVA_SINGLE, JAVA_MULTI
    };

    private Dimension gameBoardSize = null;
    private int blockSize;
    // private ArrayList<Point> point = new ArrayList<Point>(0);
    private List<Point> point = Collections.synchronizedList(new ArrayList<Point>());
    private Task task;
    private multi_Task multi_Task;
    GameOfLifeGUI gui;

    public boolean keepLiving;
    int[] birth;
    int[] survives;
    int generations;
    int speed;
    int threads;
    boolean[][] gameBoard;

    private ArrayList<LifeListener> listeners;

    /**
     * "B3/S23"
     *
     * @param birth
     * @param survives
     * @param point
     * @param gameBoardSize
     * @param blockSize
     * @param GoL
     */
    public LifeProcessor(int[] birth, int[] survives, ArrayList<Point> point, Dimension gameBoardSize, int blockSize, GameOfLifeGUI GoL) {
        this.birth = birth;
        this.survives = survives;
        this.point = point;
        this.gameBoardSize = gameBoardSize;
        this.blockSize = blockSize;
        this.gui = GoL;

        this.listeners = new ArrayList<>();
    }

    public void stopLife() {
        this.keepLiving = false;
    }

    public void cancel() {
        task.cancel(true);
        multi_Task.cancel(true);
    }

    public void processLife(int generations, ComputeMode m, int speed, int threads) {
        // Thread thread1 = new Thread(task); 
        // thread1.setName("long_task");
        this.generations = generations;
        this.speed = speed;
        this.threads = threads;
        System.out.println("speed = " + speed);
        System.out.println("threads = " + threads);

        switch (m) {
            case JAVA_SINGLE:
                // compute_java_single(generations);
                task = new Task();
                task.execute();
                // thread1.start();
                break;
            case JAVA_MULTI:
                multi_Task = new multi_Task();
                multi_Task.execute();
                // compute_java_multi(generations);
                break;
        }
    }

    public void addLifeListener(LifeListener l) {
        this.listeners.add(l);
    }

    // new thread for long running tasks -> extends swingWorker
    class Task extends SwingWorker<Void, GUIpair> {

        @Override
        public Void doInBackground() { // compute_thread_single
            // System.out.println("IN SINGLE THREAD TASK");
            compute_js(generations);
            return null;
        }

        // Updates GUI -> just updating time and generation numbers, idk where it updates the actual gameboard
        @Override
        public void process(List<GUIpair> test) {
            GUIpair latest = test.get(test.size() - 1);
            String currTime = latest.getElapsedTimeString(latest.time);
            gui.updateGUI(String.format("%d", latest.gen), currTime);
        }

        @Override
        public void done() {
            System.out.println("DONE");
        }

        // tasks below
        private void compute_js(int generations) {
            long startTime = System.currentTimeMillis();
            keepLiving = true;
            int ilive = 0;
            int movesPerSecond = 0;
            if (generations < 0) {
                movesPerSecond = -generations;
                ilive = generations - 1; // ignore the ilive (go until keepLiving is false)
            }

            while (keepLiving && ilive < generations) {
                boolean[][] gameBoard = new boolean[((gameBoardSize.width) / blockSize) + 1][((gameBoardSize.height) / blockSize) + 1];

                for (int i = 0; i < point.size(); i++) {
                    Point current = point.get(i);
                    gameBoard[current.x + 1][current.y + 1] = true;
                }

                // System.out.println("gameBoard: " + gameBoard.length + ", " + gameBoard[0].length);
                ArrayList<Point> survivingCells = new ArrayList<Point>(0);
                // Iterate through the array, follow game of life rules
                for (int i = 1; i < gameBoard.length - 1; i++) {
                    for (int j = 1; j < gameBoard[0].length - 1; j++) {
                        int surrounding = 0;
                        if (gameBoard[i - 1][j - 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i - 1][j]) {
                            surrounding++;
                        }
                        if (gameBoard[i - 1][j + 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i][j - 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i][j + 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i + 1][j - 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i + 1][j]) {
                            surrounding++;
                        }
                        if (gameBoard[i + 1][j + 1]) {
                            surrounding++;
                        }
                        if (gameBoard[i][j]) {
                            // Cell is alive, Can the cell live? (Conway, 2-3)
                            boolean survive = true;
                            for (int si = 0; si < survives.length; si++) {
                                if (survives[si] == surrounding) {
                                    // survivial!!
                                    survivingCells.add(new Point(i - 1, j - 1));
                                    break;
                                }
                            }

                        } else // Cell is dead, will the cell be given birth? (Conway, 3)
                        {
                            for (int bi = 0; bi < birth.length; bi++) {
                                if (birth[bi] == surrounding) {
                                    // survivial!!
                                    survivingCells.add(new Point(i - 1, j - 1));
                                    break;
                                }
                            }
                        }
                    }
                }

                // update the points
                point.clear();
                point.addAll(survivingCells);

                // notify listeners
                for (LifeListener l : listeners) {
                    l.lifeUpdated();
                }

                long currTime = System.currentTimeMillis() - startTime;
                publish(new GUIpair(currTime, ilive));

                if (generations > 0) {
                    ilive++;
                    try { // i still sleep here just in case and it makes things easier for me right now
                        Thread.sleep(speed);
                    } catch (InterruptedException ex) {
                        break;
                    }
                } else {
                    try {
                        Thread.sleep(1000 / movesPerSecond);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }

            }
        }
    }

    // multi-threaded task
    class multi_Task extends SwingWorker<Void, GUIpair> {

        @Override
        public Void doInBackground() { // compute_thread_single
            // System.out.println("IN SINGLE THREAD TASK");
            compute_jm(generations);
            return null;
        }

        // Updates GUI -> just updating time and generation numbers, idk where it updates the actual gameboard
        @Override
        public void process(List<GUIpair> test) {
            GUIpair latest = test.get(test.size() - 1);
            String currTime = latest.getElapsedTimeString(latest.time);
            gui.updateGUI(String.format("%d", latest.gen), currTime);
        }

        @Override
        public void done() {
            System.out.println("DONE");
        }

        // main multi-thread task 
        private void compute_jm(int generations) {
            // System.out.println("RUNNING MULTI-THREAD");
            long startTime = System.currentTimeMillis();
            keepLiving = true;
            int ilive = 0;
            int movesPerSecond = 0;

            // x -> col, y -> row
            if (generations < 0) {
                movesPerSecond = -generations;
                ilive = generations - 1; // ignore the ilive (go until keepLiving is false)
            }

            while (keepLiving && ilive < generations) {
                // System.out.println(ilive);
                gameBoard = new boolean[((gameBoardSize.width) / blockSize) + 1][((gameBoardSize.height) / blockSize) + 1];
                for (int i = 0; i < point.size(); i++) {
                    Point current = point.get(i);
                    gameBoard[current.x + 1][current.y + 1] = true;
                }
                // CopyOnWriteArrayList<Point> survivingCells = new CopyOnWriteArrayList<Point>();
                point.clear();
                // divides into individual sections for each thread -> don't know if should be in loop or not
                // as if it's in loop, can adjust to gameboard size changes in-between -> but is slower
                int tempHeight = (((gameBoardSize.height) / blockSize) + 1) / threads;
                // int tempWidth = (((gameBoardSize.width) / blockSize) + 1) / threads;

                Thread_Compute[] thr_comp = new Thread_Compute[threads];

                for (int i = 0; i < threads; i++) {
                    int temp_indx = i * tempHeight;
                    int max = temp_indx + tempHeight + 1;
                    // System.out.println("ti: " + temp_indx + " | end: " + max);
                    thr_comp[i] = new Thread_Compute(temp_indx, max);
                    thr_comp[i].start();
                }

                for (int i = 0; i < thr_comp.length; i++) {
                    try {
                        thr_comp[i].join();
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                // notify listeners 
                for (LifeListener l : listeners) {
                    l.lifeUpdated();
                }

                long currTime = System.currentTimeMillis() - startTime;
                publish(new GUIpair(currTime, ilive));

                if (generations > 0) {
                    ilive++;
                    try { // i still sleep here just in case and it makes things easier for me right now
                        Thread.sleep(speed);
                    } catch (InterruptedException ex) {
                        break;
                    }
                } else {
                    try {
                        Thread.sleep(1000 / movesPerSecond);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }

                // keepLiving = false; //debug to turn off
            }

        }

    }

    private static class GUIpair {

        private final long time;
        private final int gen;

        GUIpair(long time, int gen) {
            this.time = time;
            this.gen = gen;
        }

        public String getElapsedTimeString(long time) {
            String format = String.format("%%0%dd", 2);
            String millisecs = String.format(format, time % 1000);
            time /= 1000;
            String seconds = String.format(format, time % 60);
            String minutes = String.format(format, (time % 3600) / 60);
            String hours = String.format(format, time / 3600);
            String StrTime = hours + ":" + minutes + ":" + seconds + ":" + millisecs;
            return StrTime;
        }
    }

    // multi-threaded test -> was extending Thread
    class Thread_Compute extends Thread {

        int end_idx; // where thread stops calculating
        int thr_idx; // where thread starts calculating 
        // temp_indx, tempHeight

        public Thread_Compute(int thr_i, int i) {
            this.thr_idx = thr_i;
            this.end_idx = i;
        }

        @Override
        public void run() {
            // System.out.println("multi-threaded test, woah!");
            ArrayList<Point> survivingCells = new ArrayList<>();
            int point_index = thr_idx * (gameBoard.length - 1);

            if (thr_idx == 0) { // stops out of bounds error
                this.thr_idx = 1;
            } 
            else if (end_idx > gameBoard[0].length){
                end_idx = gameBoard[0].length; 
            }

            // Iterate through the array, follow game of life rules -> gameBoard.length in I, gameBoard[0].length in J
            for (int i = 1; i < gameBoard.length - 1; i++) {
                for (int j = thr_idx; j < end_idx - 1; j++) {
                    int surrounding = 0;
                    if (gameBoard[i - 1][j - 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i - 1][j]) {
                        surrounding++;
                    }
                    if (gameBoard[i - 1][j + 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i][j - 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i][j + 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i + 1][j - 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i + 1][j]) {
                        surrounding++;
                    }
                    if (gameBoard[i + 1][j + 1]) {
                        surrounding++;
                    }
                    if (gameBoard[i][j]) {
                        // Cell is alive, Can the cell live? (Conway, 2-3)
                        boolean survive = true;
                        for (int si = 0; si < survives.length; si++) {
                            if (survives[si] == surrounding) {
                                // survivial!!
                                survivingCells.add(new Point(i - 1, j - 1));
                                break;
                            }
                        }

                    } else // Cell is dead, will the cell be given birth? (Conway, 3)
                    {
                        for (int bi = 0; bi < birth.length; bi++) {
                            if (birth[bi] == surrounding) {
                                // survivial!!
                                survivingCells.add(new Point(i - 1, j - 1));
                                break;
                            }
                        }
                    }
                }
            }

            // update the points
            // point.clear();
            synchronized (point) {
                point.addAll(survivingCells);
            }

        }

    }

}
