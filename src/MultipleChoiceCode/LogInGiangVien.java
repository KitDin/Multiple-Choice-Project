/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DELL
 */
public class LogInGiangVien extends javax.swing.JFrame {

    private int GV_Id = 0;
    private String GV_ten = "";

    /**
     * Creates new form LogInForm
     *
     * @return
     */
    public LogInGiangVien() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bevelBorder1 = (javax.swing.border.BevelBorder)javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED);
        jPanel1 = new javax.swing.JPanel();
        btnClose = new java.awt.Label();
        label2 = new java.awt.Label();
        btnMinSreen = new java.awt.Label();
        GoBack = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputTaiKhoan = new javax.swing.JTextField();
        inputMatKhau = new javax.swing.JPasswordField();
        btnNhap = new javax.swing.JButton();
        clickTaoTaiKhoan = new javax.swing.JLabel();
        mess = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
        label2.setText("Đăng Nhập");

        btnMinSreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinSreen.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btnMinSreen.setForeground(new java.awt.Color(255, 255, 255));
        btnMinSreen.setText("_");
        btnMinSreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinSreenMouseClicked(evt);
            }
        });

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
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
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
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GoBack))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnClose.getAccessibleContext().setAccessibleName("s");

        jPanel2.setBackground(new java.awt.Color(198, 197, 185));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Tài khoản");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu");

        inputTaiKhoan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        inputMatKhau.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNhap.setBackground(new java.awt.Color(98, 146, 158));
        btnNhap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnNhap.setText("Nhập");
        btnNhap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        clickTaoTaiKhoan.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        clickTaoTaiKhoan.setText("Tạo tài khoản . . .");
        clickTaoTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clickTaoTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickTaoTaiKhoanMouseClicked(evt);
            }
        });

        mess.setAlignment(java.awt.Label.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clickTaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inputMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(mess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(mess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clickTaoTaiKhoan)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinSreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinSreenMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinSreenMouseClicked

    private void clickTaoTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickTaoTaiKhoanMouseClicked
        RegisterForm rgf = new RegisterForm();
        rgf.setVisible(true);
        rgf.pack();
        rgf.setLocationRelativeTo(null);
        rgf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_clickTaoTaiKhoanMouseClicked

    public static boolean containsSpecialCharacter(String str) {
        Pattern pattern = Pattern.compile(".*[^A-Za-z0-9 ].*");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }


    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        String LogIn = "select * from GIAO_VIEN where GV_TaiKhoan=? and GV_MatKhau=?";
        PreparedStatement stmLogIn;
        String TaiKhoan = inputTaiKhoan.getText();
        String MatKhau = inputMatKhau.getText();

        if (TaiKhoan.equals("") && MatKhau.equals("")) {
            mess.setText("Nhap day du thong tin!");
            mess.setForeground(Color.red);
        } else {
            if (TaiKhoan.length() > 15 || MatKhau.length() > 8) {
                mess.setText("Tai khoan hoac mat khau khong dung!");
                mess.setForeground(Color.red);
            } else if (containsSpecialCharacter(TaiKhoan) || containsSpecialCharacter(MatKhau)) {
                mess.setText("Tai khoan hoac mat khau khong dung!");
                mess.setForeground(Color.red);
            } else {
                try {
                    mess.setText("");
                    stmLogIn = MySQLConnect.MySQLConnect().prepareStatement(LogIn);
                    stmLogIn.setString(1, TaiKhoan);
                    stmLogIn.setString(2, MatKhau);
                    ResultSet rs = stmLogIn.executeQuery();
                    if (rs.next()) {
                        GV_Id = rs.getInt("GV_Id");
                        GV_ten = rs.getString("GV_HoTen");
                        Home hjframe = new Home(GV_ten, GV_Id);
                        hjframe.setVisible(true);
                        hjframe.pack();
                        hjframe.setLocationRelativeTo(null);
                        hjframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        this.dispose();
                    } else {
                        mess.setText("Tai khoan hoac mat khau khong dung!");
                        mess.setForeground(Color.red);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_btnNhapActionPerformed

    private void GoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GoBackMouseClicked
        ChoiceLogIn LI = new ChoiceLogIn();
        LI.setVisible(true);
        LI.pack();
        LI.setLocationRelativeTo(null);
        LI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_GoBackMouseClicked

    public int getGVId() {
        return GV_Id;
    }

    public String getGVten() {
        return GV_ten;
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
            java.util.logging.Logger.getLogger(LogInGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogInGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogInGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInGiangVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInGiangVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GoBack;
    private javax.swing.border.BevelBorder bevelBorder1;
    private java.awt.Label btnClose;
    private java.awt.Label btnMinSreen;
    private javax.swing.JButton btnNhap;
    private javax.swing.JLabel clickTaoTaiKhoan;
    private javax.swing.JPasswordField inputMatKhau;
    private javax.swing.JTextField inputTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label2;
    private java.awt.Label mess;
    // End of variables declaration//GEN-END:variables
}