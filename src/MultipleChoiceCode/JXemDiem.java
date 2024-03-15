/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import BtnAcction.TableActionCellEditor;
import BtnAcction.TableActionCellEditorXem;
import BtnAcction.TableActionCellRender;
import BtnAcction.TableActionCellRenderXem;
import BtnAcction.TableActionEvent;
import BtnAcction.TableActionEventXem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class JXemDiem extends javax.swing.JFrame {

    private String HS_Id, HS_Ten;
    private JXemDiem jXD = this;
    /**
     * Creates new form JXemDiem
     */
    public JXemDiem(String HS_Id, String HoTen) {
        initComponents();
        name.setText(HoTen);
        this.HS_Id = HS_Id;
        this.HS_Ten = HoTen;
        this.setLocationRelativeTo(null);

        TableActionEventXem event = new TableActionEventXem() {
            @Override
            public void OnXem(int row) {
                int DT = Integer.parseInt((String) jTable1.getValueAt(row, 0));
                JXemLaiCauHoiDalam LI = new JXemLaiCauHoiDalam(HS_Id, HS_Ten,DT);
                LI.setVisible(true);
                LI.pack();
                LI.setLocationRelativeTo(null);
                LI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jXD.dispose();
            }
        };
        jTable1.getColumnModel()
                .getColumn(3).setCellRenderer(new TableActionCellRenderXem());
        jTable1.getColumnModel()
                .getColumn(3).setCellEditor(new TableActionCellEditorXem(event));
        showTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new java.awt.Label();
        label2 = new java.awt.Label();
        btnMinSreen = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        GoBack = new javax.swing.JLabel();
        name = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đề thi", "Mã học phần", "Điểm", "Tác vụ"
            }
        ));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(41, 47, 54));
        jPanel1.setForeground(new java.awt.Color(41, 47, 54));

        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        label2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setText("Trang Chủ");

        btnMinSreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinSreen.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMinSreen.setForeground(new java.awt.Color(255, 255, 255));
        btnMinSreen.setText("_");
        btnMinSreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinSreenMouseClicked(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Sinh Viên");

        GoBack.setBackground(new java.awt.Color(255, 255, 255));
        GoBack.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        GoBack.setForeground(new java.awt.Color(255, 255, 255));
        GoBack.setText("<");
        GoBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GoBack.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        GoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GoBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GoBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinSreen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnMinSreen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GoBack))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        name.setText("label1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinSreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinSreenMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinSreenMouseClicked

    private void GoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoBackMouseClicked
        FormSinhVien LI = new FormSinhVien(HS_Id, HS_Ten);
        LI.setVisible(true);
        LI.pack();
        LI.setLocationRelativeTo(null);
        LI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_GoBackMouseClicked

    public void showTable() {
        try {
            String dethi = "select * from ket_qua k join de_thi d on k.DT_Id = d.DT_Id where HS_Id=?;";
            PreparedStatement stm = MySQLConnect.MySQLConnect().prepareStatement(dethi);
            stm.setString(1, HS_Id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{rs.getString("DT_Id"), rs.getString("HP_Id"), rs.getFloat("diem")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(JXemDiem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            java.util.logging.Logger.getLogger(JXemDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JXemDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JXemDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JXemDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JXemDiem("B2014993", "Dinh Tuan Kiet").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GoBack;
    private java.awt.Label btnClose;
    private java.awt.Label btnMinSreen;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label2;
    private java.awt.Label name;
    // End of variables declaration//GEN-END:variables
}
