/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import CauHoi.DeThi_CauHoi;
import CauHoi.LoadDeThi_CauHoi;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JFrame;

/**
 *
 * @author DELL
 */
public class JXemLaiCauHoiDalam extends javax.swing.JFrame {

    private int index = 0;
    private String hs_id, hs_ten;
    private int dt_id;
    private int socauhoi;
    ArrayList<DeThi_CauHoi> listQuestion;
    ArrayList<dap_an_da_lam> listDap_an_da_lam;

    /**
     * Creates new form JXemLaiCauHoiDalam
     */
    public JXemLaiCauHoiDalam(String hs_id, String hs_ten, int dt_id) {
        initComponents();
        this.hs_id = hs_id;
        this.hs_ten = hs_ten;
        this.dt_id = dt_id;
        listQuestion = new LoadDeThi_CauHoi().loadDETHI_CAUHOI(dt_id);
        listDap_an_da_lam = getDap_An_Da_Lam();
        socauhoi = getSoCauHoi();
        showCard(index);
        getDungSai();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (index < socauhoi - 1) {
                    index++;
                    showCard(index);
                }
                if (index >= socauhoi - 1) {
                    nextButton.setEnabled(false);
                }
                if (index > 0) {
                    preButton.setEnabled(true);
                }
            }
        }
        );

        preButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (index > 0) {
                    index--;
                    showCard(index);
                }
                if (index <= 0) {
                    preButton.setEnabled(false);
                }
                if (index < socauhoi) {
                    nextButton.setEnabled(true);
                }
            }
        }
        );
    }

    public void showCard(int i) {
        socau.setText("Cau " + (index + 1) + " : ");
        DeThi_CauHoi q = listQuestion.get(i);
        lblQ1.setText(q.getCH_CauHoi().toUpperCase());
        A.setText(q.getCH_DapAn1());
        B.setText(q.getCH_DapAn2());
        C.setText(q.getCH_DapAn3());
        D.setText(q.getCH_DapAn4());
        dap_an_da_lam Answer = listDap_an_da_lam.get(index);
        String selectedAnswer = Answer.getDap_An_Da_Lam();
        for (Enumeration<AbstractButton> buttons = GroupBTN.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (selectedAnswer == null) {
                GroupBTN.clearSelection();
            } else if (button.getText().trim().equalsIgnoreCase(selectedAnswer.trim())) {
                button.setSelected(true);
                break;
            }

            if (q.getDA_DapAnDung().equals(selectedAnswer)) {
                mess.setText("Đúng");
                mess.setForeground(Color.blue);
            } else {
                mess.setText("Sai");
                mess.setForeground(Color.red);

            }

        }
    }

    public String getSelectedButton() {
        for (Enumeration<AbstractButton> buttons = GroupBTN.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public int getSoCauHoi() {
        String select_CauHoi = "SELECT DISTINCT DT_SoCau FROM de_thi where DT_Id=?;";
        PreparedStatement stm;
        int a = 0;
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(select_CauHoi);
            stm.setInt(1, dt_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public void getDungSai() {
        String getDungSai = "SELECT ket_qua.KQ_SoCauDung,ket_qua.KQ_SoCauSai FROM `ket_qua` where HS_Id='" + hs_id + "' and DT_Id='" + dt_id + "';";
        try {
            PreparedStatement stm_getDungSai = MySQLConnect.MySQLConnect().prepareStatement(getDungSai);
            ResultSet rs = stm_getDungSai.executeQuery();
            while (rs.next()) {
                CauDung.setText("Số câu đúng: " + rs.getInt("KQ_SoCauDung"));
                CauSai.setText("Số câu sai : " + rs.getInt("KQ_SoCauSai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JXemLaiCauHoiDalam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupBTN = new javax.swing.ButtonGroup();
        panelA = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        label5 = new java.awt.Label();
        CauDung = new java.awt.Label();
        CauSai = new java.awt.Label();
        jblContent = new javax.swing.JPanel();
        socau = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblQ1 = new javax.swing.JTextArea();
        panel2 = new java.awt.Panel();
        D = new javax.swing.JRadioButton();
        C = new javax.swing.JRadioButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        B = new javax.swing.JRadioButton();
        A = new javax.swing.JRadioButton();
        label6 = new java.awt.Label();
        mess = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        preButton = new java.awt.Button();
        nextButton = new java.awt.Button();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelA.setLayout(new java.awt.CardLayout());

        panel1.setBackground(new java.awt.Color(41, 47, 54));

        label5.setText("label5");

        CauDung.setForeground(new java.awt.Color(255, 255, 255));
        CauDung.setText("label6");

        CauSai.setForeground(new java.awt.Color(255, 255, 255));
        CauSai.setText("label6");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CauDung, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CauSai, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CauDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CauSai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jblContent.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jblContentAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        socau.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        socau.setText("Cau 1 : ");

        lblQ1.setColumns(10);
        lblQ1.setLineWrap(true);
        lblQ1.setRows(5);
        lblQ1.setAutoscrolls(false);
        lblQ1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblQ1.setEnabled(false);
        jScrollPane1.setViewportView(lblQ1);

        GroupBTN.add(D);
        D.setText("jRadioButton1");
        D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DActionPerformed(evt);
            }
        });

        GroupBTN.add(C);
        C.setText("jRadioButton1");
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setText("A.");

        label2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label2.setText("C.");

        label3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label3.setText("B.");

        label4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label4.setText("D.");

        GroupBTN.add(B);
        B.setText("jRadioButton1");
        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        GroupBTN.add(A);
        A.setText("jRadioButton1");
        A.setName(""); // NOI18N
        A.setRequestFocusEnabled(false);
        A.setRolloverEnabled(false);
        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AActionPerformed(evt);
            }
        });

        label6.setText("label6");

        mess.setText("label7");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(mess, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                            .addComponent(A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(C, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addComponent(mess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(A))
                .addGap(20, 20, 20)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B))
                .addGap(19, 19, 19)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(C)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        preButton.setActionCommand("Next");
        preButton.setBackground(new java.awt.Color(98, 146, 158));
        preButton.setLabel("Trở về");
        preButton.setName(""); // NOI18N
        preButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preButtonActionPerformed(evt);
            }
        });

        nextButton.setActionCommand("Next");
        nextButton.setBackground(new java.awt.Color(98, 146, 158));
        nextButton.setLabel("Tiếp theo");
        nextButton.setName(""); // NOI18N
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Hoàn thanh xem lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(preButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(preButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jblContentLayout = new javax.swing.GroupLayout(jblContent);
        jblContent.setLayout(jblContentLayout);
        jblContentLayout.setHorizontalGroup(
            jblContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jblContentLayout.createSequentialGroup()
                .addGroup(jblContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jblContentLayout.createSequentialGroup()
                        .addComponent(socau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jblContentLayout.setVerticalGroup(
            jblContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jblContentLayout.createSequentialGroup()
                .addGroup(jblContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jblContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(socau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelA, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jblContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jblContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    class dap_an_da_lam {

        private int CH_Id;
        private String Dap_An_Da_Lam;

        public dap_an_da_lam(int CH_Id, String Dap_An_Da_Lam) {
            this.CH_Id = CH_Id;
            this.Dap_An_Da_Lam = Dap_An_Da_Lam;
        }

        public int getCH_Id() {
            return CH_Id;
        }

        public String getDap_An_Da_Lam() {
            return Dap_An_Da_Lam;
        }

    };

    public ArrayList<dap_an_da_lam> getDap_An_Da_Lam() {
        ArrayList<dap_an_da_lam> getDADL = new ArrayList<>();
        String getDAPAN = "select * from dap_an_da_lam where HS_Id ='" + hs_id + "' and CTDT_DT_Id ='" + dt_id + "';";
        try {
            PreparedStatement stmGet = MySQLConnect.MySQLConnect().prepareStatement(getDAPAN);
            ResultSet rs = stmGet.executeQuery();
            while (rs.next()) {
                dap_an_da_lam da = new dap_an_da_lam(rs.getInt("CTDT_CH_Id"), rs.getString("KQ_DapAn"));
                getDADL.add(da);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JXemLaiCauHoiDalam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getDADL;
    }


    private void DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DActionPerformed

    }//GEN-LAST:event_DActionPerformed

    private void CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CActionPerformed

    }//GEN-LAST:event_CActionPerformed

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed

    }//GEN-LAST:event_BActionPerformed

    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed

    }//GEN-LAST:event_AActionPerformed

    private void preButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preButtonActionPerformed

    }//GEN-LAST:event_preButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed

    }//GEN-LAST:event_nextButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JXemDiem dhp = new JXemDiem(hs_id, hs_ten);
        dhp.setVisible(true);
        dhp.pack();
        dhp.setLocationRelativeTo(null);
        dhp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jblContentAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jblContentAncestorAdded

    }//GEN-LAST:event_jblContentAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

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
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JXemLaiCauHoiDalam("", "", 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A;
    private javax.swing.JRadioButton B;
    private javax.swing.JRadioButton C;
    private java.awt.Label CauDung;
    private java.awt.Label CauSai;
    private javax.swing.JRadioButton D;
    private javax.swing.ButtonGroup GroupBTN;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jblContent;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private javax.swing.JTextArea lblQ1;
    private java.awt.Label mess;
    private java.awt.Button nextButton;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPanel panelA;
    private java.awt.Button preButton;
    private java.awt.Label socau;
    // End of variables declaration//GEN-END:variables
}
