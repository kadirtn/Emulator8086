/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author gizem
 */
public class NewJFrame extends javax.swing.JFrame {

    File openedFileToSave = null;
    boolean fileSaved = true;

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Emulate");
        jButton2.setMaximumSize(new java.awt.Dimension(71, 26));
        jButton2.setMinimumSize(new java.awt.Dimension(71, 26));
        jButton2.setPreferredSize(new java.awt.Dimension(71, 26));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Open");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(546, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (fileSaved) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "ASM Files", "asm", "txt");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Open");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileReader fr;
                try {
                    openedFileToSave = chooser.getSelectedFile();
                    fr = new FileReader(openedFileToSave);
                    jTextArea1.read(fr, "jTextArea1");
                    fileSaved = true;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Would You Like to Save your text First?", "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) { //The ISSUE is here
                if (openedFileToSave == null) {
                    JFileChooser saveFile = new JFileChooser();

                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "ASM Files", "asm");
                    saveFile.setFileFilter(filter);
                    int saveOption = saveFile.showSaveDialog(this);
                    if (saveOption == JFileChooser.APPROVE_OPTION) {

                        try {
                            String filename = saveFile.getSelectedFile().getPath();
                            if (!filename.endsWith(".asm")) {
                                filename = filename.concat(".asm");
                            }
                            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
                            fileWriter.write(jTextArea1.getText());
                            fileWriter.close();
                            fileSaved = true;
                            openedFileToSave = saveFile.getSelectedFile();

                            JFileChooser chooser2 = new JFileChooser();
                            FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                                    "ASM Files", "asm", "txt");
                            chooser2.setFileFilter(filter2);
                            chooser2.setDialogTitle("Open");
                            chooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
                            int returnVal = chooser2.showOpenDialog(this);
                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                                FileReader fr;
                                try {
                                    openedFileToSave = chooser2.getSelectedFile();
                                    fr = new FileReader(openedFileToSave);
                                    jTextArea1.read(fr, "jTextArea1");
                                    fileSaved = true;
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } catch (Exception ex) {

                        }
                    }
                } else {
                    String filename = openedFileToSave.getPath();
                    BufferedWriter fileWriter;
                    try {
                        fileWriter = new BufferedWriter(new FileWriter(filename));
                        fileWriter.write(jTextArea1.getText());
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    fileSaved = true;

                    JFileChooser chooser2 = new JFileChooser();
                    FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                            "ASM Files", "asm", "txt");
                    chooser2.setFileFilter(filter2);
                    chooser2.setDialogTitle("Open");
                    chooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int returnVal = chooser2.showOpenDialog(this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        FileReader fr;
                        try {
                            openedFileToSave = chooser2.getSelectedFile();
                            fr = new FileReader(openedFileToSave);
                            jTextArea1.read(fr, "jTextArea1");
                            fileSaved = true;
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "ASM Files", "asm", "txt");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Open");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    FileReader fr;
                    try {
                        openedFileToSave = chooser.getSelectedFile();
                        fr = new FileReader(openedFileToSave);
                        jTextArea1.read(fr, "jTextArea1");
                        fileSaved = true;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (fileSaved) {
            jTextArea1.setText("");
            openedFileToSave = null;
            fileSaved = true;
        } else {
            int dialogButton = JOptionPane.showConfirmDialog(null, "Would You Like to Save your text First?", "Warning", JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) { //The ISSUE is here
                if (openedFileToSave == null) {
                    JFileChooser saveFile = new JFileChooser();

                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "ASM Files", "asm");
                    saveFile.setFileFilter(filter);
                    int saveOption = saveFile.showSaveDialog(this);
                    if (saveOption == JFileChooser.APPROVE_OPTION) {

                        try {
                            String filename = saveFile.getSelectedFile().getPath();
                            if (!filename.endsWith(".asm")) {
                                filename = filename.concat(".asm");
                            }
                            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
                            fileWriter.write(jTextArea1.getText());
                            fileWriter.close();
                            jTextArea1.setText("");
                            fileSaved = true;
                            openedFileToSave = null;

                        } catch (Exception ex) {

                        }
                    }
                } else {
                    String filename = openedFileToSave.getPath();
                    BufferedWriter fileWriter;
                    try {
                        fileWriter = new BufferedWriter(new FileWriter(filename));
                        fileWriter.write(jTextArea1.getText());
                        fileWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fileSaved = true;
                }
            } else {
                jTextArea1.setText("");
                openedFileToSave = null;
                fileSaved = true;
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        fileSaved = false;
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (!fileSaved) {
            if (openedFileToSave != null) {
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(openedFileToSave);
                    byte[] b = jTextArea1.getText().getBytes();
                    fos.write(b);
                    fos.close();
                    fileSaved = true;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JFileChooser saveFile = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "ASM Files", "asm");
                saveFile.setFileFilter(filter);
                int saveOption = saveFile.showSaveDialog(this);
                if (saveOption == JFileChooser.APPROVE_OPTION) {

                    try {
                        String filename = saveFile.getSelectedFile().getPath();
                        if (!filename.endsWith(".asm")) {
                            filename = filename.concat(".asm");
                        }
                        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
                        fileWriter.write(jTextArea1.getText());
                        fileWriter.close();
                        fileSaved = true;
                        openedFileToSave = saveFile.getSelectedFile();

                    } catch (Exception ex) {

                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String[] lines = parseAsm();
        if (lines.length > 0) {
            new EmulatorFrame(lines).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Nothing to emulate, open an asm file or write some asm!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    private String[] parseAsm() {
        String[] lines = jTextArea1.getText().split("\\n");
        int gercekSatir = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(";")) {
                lines[i] = lines[i].substring(0, lines[i].indexOf(";"));
            }
            if (lines[i].replaceAll(" ", "").length() > 0) {
                gercekSatir++;
            }
        }
        String[] result = new String[gercekSatir];
        int resultIndex = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].replaceAll(" ", "").length() > 0) {
                result[resultIndex++] = lines[i];
            }
        }
        return result;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
