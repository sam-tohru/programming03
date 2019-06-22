/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lewi0146
 */
public class GameOfLifeGUI extends javax.swing.JFrame implements LifeListener, GameBoardListener {

    LifeProcessor lp;
    TitledBorder gbBorder;

    /**
     * Creates new form GameOfLifeGUI
     */
    public GameOfLifeGUI() {
        initComponents();

        // custome setup
        gbBorder = BorderFactory.createTitledBorder("Game of Life Board: <width, height>");
        panelGameBoard.setBorder(gbBorder);

        this.gameBoard1.addGameBoardListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGameBoard = new JPanel();
        gameBoard1 = new GameBoard();
        jButtonPlay = new JButton();
        jTextFieldElapsedTime = new JTextField();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        jComboBoxThreadMode = new JComboBox<>();
        jLabel3 = new JLabel();
        jTextFieldGenerations = new JTextField();
        jButtonLoad = new JButton();
        jButtonSave = new JButton();
        jButtonRandomFill = new JButton();
        jLabel1 = new JLabel();
        jLabel5 = new JLabel();
        jTextFieldBirths = new JTextField();
        jTextFieldBlockSize = new JTextField();
        jLabelSurvives = new JLabel();
        jCheckBoxGridOn = new JCheckBox();
        jTextFieldSurvives = new JTextField();
        jTextFieldFile = new JTextField();
        jComboBoxFillPercentage = new JComboBox<>();
        jLabel6 = new JLabel();
        jTextFieldSpeed = new JTextField();
        jLabel7 = new JLabel();
        jTextFieldThreads = new JTextField();
        jLabel8 = new JLabel();
        jButtonStop = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panelGameBoard.setBackground(new Color(255, 255, 255));
        panelGameBoard.setBorder(BorderFactory.createTitledBorder("Game of Life Board: <width, height>"));

        gameBoard1.setBackground(new Color(255, 255, 255));

        GroupLayout gameBoard1Layout = new GroupLayout(gameBoard1);
        gameBoard1.setLayout(gameBoard1Layout);
        gameBoard1Layout.setHorizontalGroup(gameBoard1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gameBoard1Layout.setVerticalGroup(gameBoard1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 331, Short.MAX_VALUE)
        );

        GroupLayout panelGameBoardLayout = new GroupLayout(panelGameBoard);
        panelGameBoard.setLayout(panelGameBoardLayout);
        panelGameBoardLayout.setHorizontalGroup(panelGameBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameBoard1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGameBoardLayout.setVerticalGroup(panelGameBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameBoard1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButtonPlay.setText("Play");
        jButtonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jTextFieldElapsedTime.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldElapsedTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldElapsedTimeActionPerformed(evt);
            }
        });

        jLabel2.setText("Elapsed Time:");

        jLabel4.setText("hh:mm:ss:SS");

        jComboBoxThreadMode.setModel(new DefaultComboBoxModel<>(new String[] { "Single Thread", "Multithreaded" }));

        jLabel3.setText("Generations");

        jTextFieldGenerations.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldGenerations.setText("10");
        jTextFieldGenerations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldGenerationsActionPerformed(evt);
            }
        });

        jButtonLoad.setText("Load");
        jButtonLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonRandomFill.setText("Random Fill");
        jButtonRandomFill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonRandomFillActionPerformed(evt);
            }
        });

        jLabel1.setText("B:");

        jLabel5.setText("Block Size:");

        jTextFieldBirths.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldBirths.setText("3");

        jTextFieldBlockSize.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldBlockSize.setText("5");
        jTextFieldBlockSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldBlockSizeActionPerformed(evt);
            }
        });

        jLabelSurvives.setText("S:");

        jCheckBoxGridOn.setSelected(true);
        jCheckBoxGridOn.setText("Grid On");
        jCheckBoxGridOn.setHorizontalTextPosition(SwingConstants.LEADING);
        jCheckBoxGridOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jCheckBoxGridOnActionPerformed(evt);
            }
        });

        jTextFieldSurvives.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldSurvives.setText("2,3");

        jTextFieldFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldFileActionPerformed(evt);
            }
        });

        jComboBoxFillPercentage.setModel(new DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90", "95" }));

        jLabel6.setText("%");

        jTextFieldSpeed.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldSpeed.setText("100");
        jTextFieldSpeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldSpeedActionPerformed(evt);
            }
        });

        jLabel7.setText("Speed:");

        jTextFieldThreads.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldThreads.setText("4");
        jTextFieldThreads.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextFieldThreadsActionPerformed(evt);
            }
        });

        jLabel8.setText("Threads: ");

        jButtonStop.setText("Stop");
        jButtonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBirths, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelSurvives)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSurvives, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldGenerations, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxThreadMode, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldElapsedTime, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(panelGameBoard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(4, 4, 4)
                                .addComponent(jTextFieldBlockSize, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxGridOn, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(3, 3, 3)
                                .addComponent(jTextFieldSpeed, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(6, 6, 6)
                                .addComponent(jTextFieldThreads, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jButtonStop)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxFillPercentage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRandomFill)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldFile, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLoad)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSave)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRandomFill)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBlockSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxGridOn)
                    .addComponent(jTextFieldFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad)
                    .addComponent(jButtonSave)
                    .addComponent(jComboBoxFillPercentage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldThreads, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButtonStop))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGameBoard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlay)
                    .addComponent(jTextFieldElapsedTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxThreadMode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldGenerations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldBirths, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSurvives)
                    .addComponent(jTextFieldSurvives, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // play button push 
    private void jButtonPlayActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed

        String[] birthS = this.jTextFieldBirths.getText().split(",");
        int[] birth = new int[birthS.length];
        for (int i = 0; i < birthS.length; i++) {
            birth[i] = Integer.parseInt(birthS[i].trim());
        }

        String[] survivesS = this.jTextFieldSurvives.getText().split(",");
        int[] survives = new int[survivesS.length];
        for (int i = 0; i < survivesS.length; i++) {
            survives[i] = Integer.parseInt(survivesS[i].trim());
        }
        
        

        lp = new LifeProcessor(birth, survives, this.gameBoard1.getPoints(), this.gameBoard1.getSize(), this.gameBoard1.getBlockSize(), this);
        lp.addLifeListener(this);

        int generations = Integer.parseInt(this.jTextFieldGenerations.getText());
        int speed = Integer.parseInt(this.jTextFieldSpeed.getText());
        int threads = Integer.parseInt(this.jTextFieldThreads.getText()); 
        LifeProcessor.ComputeMode computeMode = LifeProcessor.ComputeMode.class.getEnumConstants()[this.jComboBoxThreadMode.getSelectedIndex()];
        
        // long startTime = System.currentTimeMillis();
        lp.processLife(generations, computeMode, speed, threads);
        // long endTime = System.currentTimeMillis();
        // this.jTextFieldElapsedTime.setText(getElapsedTimeString(endTime - startTime));


    }//GEN-LAST:event_jButtonPlayActionPerformed

    public void updateGUI(String newGen, String newTime){
        this.jTextFieldGenerations.setText(newGen); 
        this.jTextFieldElapsedTime.setText(newTime);
    }
    
    
    private void jTextFieldBlockSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBlockSizeActionPerformed

        int blockSize = Integer.parseInt(this.jTextFieldBlockSize.getText());

        gameBoard1.updateBlockSize(blockSize);
    }//GEN-LAST:event_jTextFieldBlockSizeActionPerformed

    private void jCheckBoxGridOnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBoxGridOnActionPerformed

        gameBoard1.updateDrawGrid(this.jCheckBoxGridOn.isSelected());

    }//GEN-LAST:event_jCheckBoxGridOnActionPerformed

    private void jButtonSaveActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        String filename = this.jTextFieldFile.getText();

        try {
            gameBoard1.saveGameBoard(filename);
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this, "Error saving to file " + filename + ": " + ex.getMessage());

        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonRandomFillActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonRandomFillActionPerformed

        int percentage = Integer.parseInt((String) this.jComboBoxFillPercentage.getSelectedItem());

        this.gameBoard1.randomlyFillBoard(percentage);
    }//GEN-LAST:event_jButtonRandomFillActionPerformed

    private void jButtonLoadActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        loadFromFile();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jTextFieldFileActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jTextFieldFileActionPerformed
        loadFromFile();
    }//GEN-LAST:event_jTextFieldFileActionPerformed

    private void jTextFieldElapsedTimeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jTextFieldElapsedTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldElapsedTimeActionPerformed

    private void jTextFieldGenerationsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jTextFieldGenerationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGenerationsActionPerformed

    private void jTextFieldSpeedActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jTextFieldSpeedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSpeedActionPerformed

    private void jTextFieldThreadsActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jTextFieldThreadsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldThreadsActionPerformed

    private void jButtonStopActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        System.out.println("STOPPING"); 
        lp.stopLife();
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void loadFromFile() {
        String filename = this.jTextFieldFile.getText();

        try {
            gameBoard1.loadGameBoard(filename);
        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this, "Error reading from file " + filename + ": " + ex.getMessage());

        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error parsing from file " + filename + ": " + ex.getMessage());
        }

    }

    public String getElapsedTimeString(long elapsedTime) {
        String format = String.format("%%0%dd", 2);
        String millisecs = String.format(format, elapsedTime % 1000);
        elapsedTime /= 1000;
        String seconds = String.format(format, elapsedTime % 60);
        String minutes = String.format(format, (elapsedTime % 3600) / 60);
        String hours = String.format(format, elapsedTime / 3600);
        String time = hours + ":" + minutes + ":" + seconds + ":" + millisecs;
        return time;
    }

    @Override
    public void lifeUpdated() {
        gameBoard1.repaint();
    }

    @Override
    public void gameBoardDimensionUpdated(Dimension gameBoardSize) {
        this.gbBorder.setTitle("Game of Life Board: " + gameBoardSize.getWidth() + ", " + gameBoardSize.getHeight());
        this.panelGameBoard.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLifeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GameBoard gameBoard1;
    private JButton jButtonLoad;
    private JButton jButtonPlay;
    private JButton jButtonRandomFill;
    private JButton jButtonSave;
    private JButton jButtonStop;
    private JCheckBox jCheckBoxGridOn;
    private JComboBox<String> jComboBoxFillPercentage;
    private JComboBox<String> jComboBoxThreadMode;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabelSurvives;
    private JTextField jTextFieldBirths;
    private JTextField jTextFieldBlockSize;
    private JTextField jTextFieldElapsedTime;
    private JTextField jTextFieldFile;
    private JTextField jTextFieldGenerations;
    private JTextField jTextFieldSpeed;
    private JTextField jTextFieldSurvives;
    private JTextField jTextFieldThreads;
    private JPanel panelGameBoard;
    // End of variables declaration//GEN-END:variables

}
