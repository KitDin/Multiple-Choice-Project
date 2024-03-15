/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import HocKy_NamHoc.HOCKY_NAMHOC;
import java.awt.Color;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class EditHocPhan extends javax.swing.JFrame {

    private String TenGV = "";
    private String IDHocPhan = "";
    private int IDGV = 0;
    EditHocPhan hp = null;

    /**
     * Creates new form EditHocPhan
     *
     * @param HP_Id
     * @param HP_TenHocPhan
     * @param HKNK_Nam
     * @param HKNK_HocKy
     * @param Lop
     * @param Getten
     * @param GetId
     * @param GetIDHocPhan
     * @param h
     */
    public EditHocPhan(String HP_Id, String HP_TenHocPhan, String HKNK_Nam, String HKNK_HocKy, String Lop, String Getten, int GetId, String GetIDHocPhan) {

        initComponents();
        TenGV = Getten;
        IDGV = GetId;
        IDHocPhan = GetIDHocPhan;
        this.setLocationRelativeTo(null);
        if (HKNK_HocKy.equals("Chưa sắp xếp chi tiết giảng dạy") || HKNK_Nam.equals("Chưa sắp xếp chi tiết giảng dạy")) {
            JOptionPane.showMessageDialog(null, "Cần thêm chi tiết giảng dạy cho Học Phần này");
            new Home(TenGV, IDGV).setVisible(true);
            hp.dispose();
        } else {

            this.setLocationRelativeTo(null);
            TenHocPhan.setText(HP_TenHocPhan);
            try {
                PreparedStatement smtHK = MySQLConnect.MySQLConnect().prepareStatement("select distinct HKNK_HocKi from HOC_KI_NIEN_KHOA;");
                PreparedStatement smtNH = MySQLConnect.MySQLConnect().prepareStatement("select distinct HKNK_NamHoc from HOC_KI_NIEN_KHOA;");
                PreparedStatement stmLop = MySQLConnect.MySQLConnect().prepareStatement("SELECT DISTINCT LOP_Id FROM `lop`;");
                ResultSet rsHK = smtHK.executeQuery();
                while (rsHK.next()) {
                    if (HKNK_HocKy.equals(rsHK.getString("HKNK_HocKi"))) {
                        choiceHK.addItem(HKNK_HocKy);
                        choiceHK.select(HKNK_HocKy);
                    } else {
                        choiceHK.addItem(rsHK.getString("HKNK_HocKi"));
                    }
                }
                ResultSet rsNH = smtNH.executeQuery();
                while (rsNH.next()) {
                    if (HKNK_Nam.equals(rsNH.getString("HKNK_NamHoc"))) {
                        choiceNH.addItem(HKNK_Nam);
                        choiceNH.select(HKNK_Nam);
                    } else {
                        choiceNH.add(rsNH.getString("HKNK_NamHoc"));
                    }
                }
                ResultSet rsLop = stmLop.executeQuery();
                while (rsLop.next()) {
                    if (Lop.equals(rsLop.getString("LOP_Id"))) {
                        LopID.addItem(Lop);
                        LopID.select(Lop);
                    } else {
                        LopID.add(rsLop.getString("LOP_Id"));

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditHocPhan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnClose2 = new java.awt.Label();
        btnMinSreen2 = new java.awt.Label();
        edit = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        choiceNH = new java.awt.Choice();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        TenHocPhan = new javax.swing.JTextField();
        label5 = new java.awt.Label();
        choiceHK = new java.awt.Choice();
        Edit = new java.awt.Button();
        Huy = new java.awt.Button();
        label6 = new java.awt.Label();
        mess = new javax.swing.JLabel();
        LopID = new java.awt.Choice();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(41, 47, 54));
        jPanel3.setForeground(new java.awt.Color(41, 47, 54));

        btnClose2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnClose2.setForeground(new java.awt.Color(255, 255, 255));
        btnClose2.setText("X");
        btnClose2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClose2MouseClicked(evt);
            }
        });

        btnMinSreen2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinSreen2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMinSreen2.setForeground(new java.awt.Color(255, 255, 255));
        btnMinSreen2.setText("_");
        btnMinSreen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinSreen2MouseClicked(evt);
            }
        });

        edit.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Sửa Thông Tin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinSreen2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnMinSreen2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(198, 197, 185));

        label3.setText("Tên học phần");

        label4.setText("Năm học");

        label5.setText("Học kỳ");

        Edit.setLabel("Sửa");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Huy.setLabel("Huỷ");
        Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyActionPerformed(evt);
            }
        });

        label6.setText("Lớp");

        mess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(choiceNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(choiceHK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TenHocPhan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(LopID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addComponent(mess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(mess, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TenHocPhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LopID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choiceHK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choiceNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Huy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClose2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose2MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnClose2MouseClicked

    private void btnMinSreen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinSreen2MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinSreen2MouseClicked

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        String Ma, Ten, HK, NH, L;
        Ten = TenHocPhan.getText();
        HK = choiceHK.getSelectedItem();
        NH = choiceNH.getSelectedItem();
        L = LopID.getSelectedItem();

        String Update = "{call edit_Hocphan(?,?,?,?,?,?,?)}";
        String check = "select * from hoc_phan where HP_Id='" + IDHocPhan + "'";
        CallableStatement stmUpdate;
        PreparedStatement stmcheck;

        try {
          
                stmUpdate = MySQLConnect.MySQLConnect().prepareCall(Update);
                stmUpdate.setString(1, NH);
                stmUpdate.setString(2, HK);
                stmUpdate.setString(3, IDHocPhan);
                stmUpdate.setString(4, L);
                stmUpdate.setString(5, Ten);
                stmUpdate.setString(6, IDHocPhan);
                stmUpdate.setInt(7, IDGV);
                stmUpdate.executeUpdate();
                JOptionPane.showMessageDialog(this, "Đã cập nhật thành công");
                new Home(TenGV, IDGV).setVisible(true);
                this.dispose();
            

        } catch (SQLException ex) {
            Logger.getLogger(EditHocPhan.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println(" "+Ma+" "+Ten+" "+HK+" "+NH+" "+L+" "+IDHocPhan+" "+IDGV);
//        PreparedStatement stmUpdateHk;
//        String UpdateHK = "update CHI_TIET_GIANG_DAY set HKNK_NamHoc= ? ,HKNK_HocKi = ? , LOP_Id = ?  where HP_Id=?;";
//        try {
//            stmUpdateHk = MySQLConnect.MySQLConnect().prepareStatement(UpdateHK);
//            stmUpdateHk.setString(1, NH);
//            stmUpdateHk.setString(2, HK);
//            stmUpdateHk.setString(3, L);
//            stmUpdateHk.setString(4, IDHocPhan);
//            stmUpdateHk.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(EditHocPhan.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        PreparedStatement stmUpdateHp;
//        String UpdateHP = "update HOC_PHAN set HP_Id=?,HP_TenHocPhan=? where HP_Id=? and GV_Id=?;";
//        try {
//            stmUpdateHp = MySQLConnect.MySQLConnect().prepareCall(UpdateHP);
//            stmUpdateHp.setString(1, Ma);
//            stmUpdateHp.setString(2, Ten);
//            stmUpdateHp.setString(3, IDHocPhan);
//            stmUpdateHp.setInt(4, IDGV);
//            stmUpdateHp.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(EditHocPhan.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_EditActionPerformed

    private void HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyActionPerformed
        new Home(TenGV, IDGV).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HuyActionPerformed

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
            java.util.logging.Logger.getLogger(EditHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditHocPhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditHocPhan("", "", "", "", "", "", 0, "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Edit;
    private java.awt.Button Huy;
    private java.awt.Choice LopID;
    private javax.swing.JTextField TenHocPhan;
    private java.awt.Label btnClose2;
    private java.awt.Label btnMinSreen2;
    private java.awt.Choice choiceHK;
    private java.awt.Choice choiceNH;
    private java.awt.Label edit;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private javax.swing.JLabel mess;
    // End of variables declaration//GEN-END:variables
}
