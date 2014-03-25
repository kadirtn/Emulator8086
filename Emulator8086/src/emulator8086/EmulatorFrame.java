/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emulator8086;

import line.Komut;
import line.FonksiyonTanimi;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import line.Line;
import line.Variable;
import steps.CareTaker;

/**
 *
 * @author PRowLeR
 */
public class EmulatorFrame extends javax.swing.JFrame {

    /**
     * Creates new form EmulatorFrame
     */
    public EmulatorFrame() {
        initComponents();
    }
    public MemoryView systemMemory;
    public HashMap<String, Integer> functionMap;
    public static HashMap<String, Memory> variableMap;
    Object[] komutList = null;
    CareTaker careTaker = null;
    int stepPointer;

    public EmulatorFrame(String[] listContent) {
        initComponents();
        stepPointer = 0;
        careTaker = new CareTaker();
        systemMemory = new MemoryView(1024);
        functionMap = new HashMap<String, Integer>();
        variableMap = new HashMap<String, Memory>();
        komutList = asmToLineList(listContent);
        System.out.println("function size" + functionMap.size());
        System.out.println("function" + functionMap.get("k1"));
        for (int i = 0; i < komutList.length; i++) {
            System.out.println(i + ": " + ((Line) komutList[i]).toString());
        }
        careTaker.kaydet(0);
        executeKomuts();// memory ye komutlar doldurulmak isteniyorsa

        //asm JList doldurma
        jList2 = new javax.swing.JList(komutList);
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList2);

        //bellek JList doldurma
        jList1 = new javax.swing.JList(systemMemory.getList());
        jList1.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, false,
                        false);

                return this;
            }
        });
        jScrollPane2.setViewportView(jList1);
        load();

    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        registerAH = new javax.swing.JTextField();
        registerAL = new javax.swing.JTextField();
        registerBL = new javax.swing.JTextField();
        registerBH = new javax.swing.JTextField();
        registerCL = new javax.swing.JTextField();
        registerCH = new javax.swing.JTextField();
        registerDL = new javax.swing.JTextField();
        registerDH = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        flagCF = new javax.swing.JTextField();
        flagZF = new javax.swing.JTextField();
        flagSF = new javax.swing.JTextField();
        flagOF = new javax.swing.JTextField();
        flagPF = new javax.swing.JTextField();
        flagDF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Reload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Step Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepBack(evt);
            }
        });

        jButton4.setText("Single Step");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singleStep(evt);
            }
        });

        jButton5.setText("Run");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registers"));

        registerAH.setEditable(false);
        registerAH.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerAH.setText("00h");
        registerAH.setPreferredSize(new java.awt.Dimension(24, 20));

        registerAL.setEditable(false);
        registerAL.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerAL.setText("00h");
        registerAL.setPreferredSize(new java.awt.Dimension(24, 20));
        registerAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerALActionPerformed(evt);
            }
        });

        registerBL.setEditable(false);
        registerBL.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerBL.setText("00h");
        registerBL.setPreferredSize(new java.awt.Dimension(24, 20));

        registerBH.setEditable(false);
        registerBH.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerBH.setText("00h");
        registerBH.setPreferredSize(new java.awt.Dimension(24, 20));

        registerCL.setEditable(false);
        registerCL.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerCL.setText("00h");
        registerCL.setPreferredSize(new java.awt.Dimension(24, 20));

        registerCH.setEditable(false);
        registerCH.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerCH.setText("00h");
        registerCH.setPreferredSize(new java.awt.Dimension(24, 20));

        registerDL.setEditable(false);
        registerDL.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerDL.setText("00h");
        registerDL.setPreferredSize(new java.awt.Dimension(24, 20));

        registerDH.setEditable(false);
        registerDH.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        registerDH.setText("00h");
        registerDH.setToolTipText("");
        registerDH.setPreferredSize(new java.awt.Dimension(24, 20));

        jLabel1.setText("AX");

        jLabel2.setText("H");

        jLabel3.setText("L");

        jLabel4.setText("BX");

        jLabel5.setText("CX");

        jLabel6.setText("DX");

        jLabel7.setText("CF");

        jLabel8.setText("ZF");

        jLabel9.setText("SF");

        jLabel10.setText("OF");

        jLabel11.setText("PF");

        jLabel12.setText("DF");

        flagCF.setEditable(false);
        flagCF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagCF.setText("00h");
        flagCF.setPreferredSize(new java.awt.Dimension(24, 20));

        flagZF.setEditable(false);
        flagZF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagZF.setText("00h");
        flagZF.setPreferredSize(new java.awt.Dimension(24, 20));

        flagSF.setEditable(false);
        flagSF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagSF.setText("00h");
        flagSF.setPreferredSize(new java.awt.Dimension(24, 20));

        flagOF.setEditable(false);
        flagOF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagOF.setText("00h");
        flagOF.setPreferredSize(new java.awt.Dimension(24, 20));

        flagPF.setEditable(false);
        flagPF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagPF.setText("00h");
        flagPF.setPreferredSize(new java.awt.Dimension(24, 20));
        flagPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flagPFActionPerformed(evt);
            }
        });

        flagDF.setEditable(false);
        flagDF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        flagDF.setText("00h");
        flagDF.setPreferredSize(new java.awt.Dimension(24, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerAH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerBH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerCH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagCF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagZF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagOF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flagDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerAL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerBL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerCL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registerDL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerAH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(registerAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(registerBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(flagCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(flagZF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(flagSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(flagOF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(flagPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(flagDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bellek"));

        jList1.setEnabled(false);
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Assembly"));

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setEnabled(false);
        jScrollPane3.setViewportView(jList2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:,
        stepPointer = 0;
        load();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void flagPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flagPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flagPFActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void registerALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerALActionPerformed

    private void singleStep(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singleStep
        // TODO add your handling code here:
        stepPointer++;
        load();
    }//GEN-LAST:event_singleStep

    private void stepBack(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepBack
        // TODO add your handling code here:
        stepPointer--;
        load();
    }//GEN-LAST:event_stepBack

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        stepPointer = komutList.length;
        load();
        stepPointer = komutList.length + 1;
        load();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(EmulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmulatorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField flagCF;
    private javax.swing.JTextField flagDF;
    private javax.swing.JTextField flagOF;
    private javax.swing.JTextField flagPF;
    private javax.swing.JTextField flagSF;
    private javax.swing.JTextField flagZF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField registerAH;
    private javax.swing.JTextField registerAL;
    private javax.swing.JTextField registerBH;
    private javax.swing.JTextField registerBL;
    private javax.swing.JTextField registerCH;
    private javax.swing.JTextField registerCL;
    private javax.swing.JTextField registerDH;
    private javax.swing.JTextField registerDL;
    // End of variables declaration//GEN-END:variables

    private Object[] asmToLineList(String[] listContent) {
        Object[] resultList = new Object[listContent.length];
        for (int i = 0; i < listContent.length; i++) {
            StringTokenizer st = new StringTokenizer(listContent[i], " ,");
            List<String> tokens = new ArrayList<String>();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            if (tokens.size() > 1
                    && (tokens.get(1).toLowerCase().equals("db"))) {
                List<Integer> variables = new ArrayList<>();
                for (int j = 2; j < tokens.size(); j++) {
                    variables.add(isAValue(tokens.get(j)));
                }
                variableMap.put(tokens.get(0), new Memory(Memory.VariableType.DB, variables));

                Line var = new Variable(listContent[i], 1, tokens.get(0), i);
                resultList[i] = var;
            } else if (tokens.size() > 1
                    && (tokens.get(1).toLowerCase().equals("dw"))) {
                List<Integer> variables = new ArrayList<>();
                for (int j = 2; j < tokens.size(); j++) {
                    variables.add(isAValue(tokens.get(j)));
                }
                variableMap.put(tokens.get(1), new Memory(Memory.VariableType.DW, variables));
                Line var = new Variable(listContent[i], 2, tokens.get(0), i);
                resultList[i] = var;

            } else if (listContent[i].contains(":")) {//Fonksiyon Tanimi
                Line yeniFonksiyonTanimi = new FonksiyonTanimi(listContent[i], listContent[i].substring(0, listContent[i].indexOf(":")), i);
                functionMap.put(listContent[i].substring(0, listContent[i].indexOf(":")), i);
                resultList[i] = yeniFonksiyonTanimi;
            } else {
                Line yeniKomut = new Komut(listContent[i], tokens.get(0), i);
                for (int j = 1; j < tokens.size(); j++) {
                    String degisken = tokens.get(j);
                    int value = isAValue(degisken);
                    if (isARegister(degisken)) {//Register
                        ((Komut) yeniKomut).addDegisken(new Degisken(degisken));
                    } else if (value != -1) {//Immediate
                        ((Komut) yeniKomut).addDegisken(new Degisken(value));
                    } else if(tokens.get(0).equals("LOOP")){
                        ((Komut) yeniKomut).functionLine = functionMap.get(tokens.get(1));
                    }
                    else {//Memory
                        if (degisken.contains("[") && degisken.contains("]")) {

                            ((Komut) yeniKomut).addDegisken(new Degisken(degisken.substring(0, degisken.indexOf("[")), variableMap.get(degisken.substring(0, degisken.indexOf("["))).getType() == Memory.VariableType.DB ? 1 : 2, Integer.parseInt(degisken.substring(degisken.indexOf("[") + 1, degisken.indexOf("]")))));
                        } else {
                            ((Komut) yeniKomut).addDegisken(new Degisken(degisken, variableMap.get(degisken).getType() == Memory.VariableType.DB ? 1 : 2, 0));
                        }
                    }
                }
                resultList[i] = yeniKomut;
            }

        }
        return resultList;
    }

    private int isAValue(String degisken) {
        try {
            if (degisken.length() > 0) {
                if (degisken.endsWith("H")) {
                    return Integer.parseInt(degisken.substring(0, degisken.length() - 1), 16);
                } else {
                    return Integer.parseInt(degisken);
                }
            }
        } catch (Exception e) {
            System.out.println(degisken + " is not a value");
        }
        return -1;
    }

    private boolean isARegister(String degisken) {
        return Register.getRegister().getValue(degisken) != null;
    }

    private void executeKomuts() {
        int satir = 0;
        try {
            while (true) {
                Line line = (Line) komutList[satir];
                if (line instanceof Variable) {
                    satir++;
                } else if (line instanceof FonksiyonTanimi) {
                    satir++;
                } else {
                    satir = komutIslet(satir, (Komut) line);
                }
                if(satir == -1)
                    break;
                careTaker.kaydet(satir);
                
                if (satir == komutList.length) {
                    break;
                }
            }
        } catch (Exception ex) {  
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private int komutIslet(int satir, Komut komut) throws Exception {
        //gelen komutların ilgili instruction a yönlendirilip işletilmesi
        switch (komut.komut) {
            case "ADC":
                return Instructions.ADC(satir, komut);
            case "ADD":
                return Instructions.ADD(satir, komut);
            case "AND":
                return Instructions.AND(satir, komut);
            case "CLC":
                return Instructions.CLC(satir, komut);
            case "CLD":
                return Instructions.CLD(satir, komut);
            case "CMP":
                return Instructions.CMP(satir, komut);
            case "DEC":
                return Instructions.DEC(satir, komut);
            case "DIV":
                return Instructions.DIV(satir, komut);
            case "HLT":
                return Instructions.HLT(satir, komut);
            case "IDIV":
                return Instructions.IDIV(satir, komut);
            case "IMUL":
                return Instructions.IMUL(satir, komut);
            case "INC":
                return Instructions.INC(satir, komut);
            case "JA":
                return Instructions.JA(satir, komut);
            case "JAE":
                return Instructions.JAE(satir, komut);
            case "JB":
                return Instructions.JB(satir, komut);
            case "JBE":
                return Instructions.JBE(satir, komut);
            case "JE":
                return Instructions.JE(satir, komut);
            case "JG":
                return Instructions.JG(satir, komut);
            case "JGE":
                return Instructions.JGE(satir, komut);
            case "JL":
                return Instructions.JL(satir, komut);
            case "JLE":
                return Instructions.JLE(satir, komut);
            case "JMP":
                return Instructions.JMP(satir, komut);
            case "JNE":
                return Instructions.JNE(satir, komut);
            case "JNP":
                return Instructions.JNP(satir, komut);
            case "JP":
                return Instructions.JP(satir, komut);
            case "JPO":
                return Instructions.JPO(satir, komut);
            case "LEA":
                return Instructions.LEA(satir, komut);
            case "LOOP":
                return Instructions.LOOP(satir, komut);
            case "MOV":
                return Instructions.MOV(satir, komut);
            case "MUL":
                return Instructions.MUL(satir, komut);
            case "NEG":
                return Instructions.NEG(satir, komut);
            case "NOP":
                return Instructions.NOP(satir, komut);
            case "NOT":
                return Instructions.NOT(satir, komut);
            case "OR":
                return Instructions.OR(satir, komut);
            case "POP":
                return Instructions.POP(satir, komut);
            case "PUSH":
                return Instructions.PUSH(satir, komut);
            case "ROL":
                return Instructions.ROL(satir, komut);
            case "ROR":
                return Instructions.ROR(satir, komut);
            case "SBB":
                return Instructions.SBB(satir, komut);
            case "SHL":
                return Instructions.SHL(satir, komut);
            case "SHR":
                return Instructions.SHR(satir, komut);
            case "STC":
                return Instructions.STC(satir, komut);
            case "STD":
                return Instructions.STD(satir, komut);
            case "SUB":
                return Instructions.SUB(satir, komut);
            case "XOR":
                return Instructions.XOR(satir, komut);
        }
        return -1;
    }

    public void load() {
        if (careTaker.getSize() <= stepPointer) {
            JOptionPane.showMessageDialog(this, "Done Emulating!", "Complete", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            careTaker.load(stepPointer);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        jList2.setSelectedIndex(careTaker.get(stepPointer).satir);
        registerAH.setText(Register.getRegister().getHexValue("AH"));
        registerAL.setText(Register.getRegister().getHexValue("AL"));
        registerBH.setText(Register.getRegister().getHexValue("BH"));
        registerBL.setText(Register.getRegister().getHexValue("BL"));
        registerCH.setText(Register.getRegister().getHexValue("CH"));
        registerCL.setText(Register.getRegister().getHexValue("CL"));
        registerDH.setText(Register.getRegister().getHexValue("DH"));
        registerDL.setText(Register.getRegister().getHexValue("DL"));
        flagCF.setText(Flag.getFlag().CF ? "1" : "0");
        flagDF.setText(Flag.getFlag().DF ? "1" : "0");
        flagOF.setText(Flag.getFlag().OF ? "1" : "0");
        flagPF.setText(Flag.getFlag().PF ? "1" : "0");
        flagSF.setText(Flag.getFlag().SF ? "1" : "0");
        flagZF.setText(Flag.getFlag().ZF ? "1" : "0");
    }

}
