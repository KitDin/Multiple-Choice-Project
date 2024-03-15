/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import HocPhan.loadHOC_PHANs;
import HocPhan.HOC_PHANs;
import BtnAcction.AcctionCell;
import BtnAcction.BtnAcctionEvent;
import BtnAcction.BtnCellEditor;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Home extends javax.swing.JFrame {

    int GV_Id;
    String GV_HoTen;
    Home h = this;

    /**
     * Creates new form Home
     */
    public Home() {

    }

    public Home(String Getten, int GetId) {
        initComponents();
        HocPhan.setSelected(rootPaneCheckingEnabled);
        GV_HoTen = Getten;
        GV_Id = GetId;
        tillte.setText("Chào " + GV_HoTen);
        this.setLocationRelativeTo(null);
        BtnAcctionEvent event = new BtnAcctionEvent() {
            @Override
            public void onDelete(int row) {
                String MaHocPhan = (String) jTable1.getValueAt(row, 0);
                String TenHocPhan = (String) jTable1.getValueAt(row, 1);
                int option = JOptionPane.showConfirmDialog(
                        new Home(),
                        "Bạn có muốn xoá học phần " + TenHocPhan,
                        "Xoá học phần " + MaHocPhan,
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_NO_OPTION) {
                    String Delete_HocPhan = "delete from cauhoitracnghiem.hoc_phan where HP_Id=? and GV_Id=?;";
                    try {
                        PreparedStatement smtDelete = MySQLConnect.MySQLConnect().prepareStatement(Delete_HocPhan);
                        smtDelete.setString(1, MaHocPhan);
                        smtDelete.setInt(2, GV_Id);
                        boolean a = smtDelete.execute();
                        if (jTable1.isEditing()) {
                            jTable1.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.removeRow(row);
                    } catch (SQLException ex) {
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (option == JOptionPane.NO_OPTION) {
                    System.out.println("MultipleChoiceCode.Home.<init>()");
                }
            }

            @Override
            public void onEdit(int row) {
                String MaHocPhan = (String) jTable1.getValueAt(row, 0);
                String TenHocPhan = (String) jTable1.getValueAt(row, 1);
                String HocKy = (String) jTable1.getValueAt(row, 2);
                String NamHoc = (String) jTable1.getValueAt(row, 3);
                String Lop = (String) jTable1.getValueAt(row, 4);
                h.dispose();
                EditHocPhan dhp = new EditHocPhan(MaHocPhan, TenHocPhan, NamHoc, HocKy, Lop, Getten, GetId, MaHocPhan);
                dhp.setVisible(true);
                dhp.pack();
                dhp.setLocationRelativeTo(null);
                dhp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        };

        jTable1.getColumnModel()
                .getColumn(5).setCellRenderer(new AcctionCell());
        jTable1.getColumnModel()
                .getColumn(5).setCellEditor(new BtnCellEditor(event));
        showHOC_PHAN();

        try {
            PreparedStatement smtHK = MySQLConnect.MySQLConnect().prepareStatement("select distinct HKNK_HocKi from HOC_KI_NIEN_KHOA;");
            PreparedStatement smtNH = MySQLConnect.MySQLConnect().prepareStatement("select distinct HKNK_NamHoc from HOC_KI_NIEN_KHOA;");
            PreparedStatement stmLop = MySQLConnect.MySQLConnect().prepareStatement("SELECT DISTINCT LOP_Id FROM `lop`;");
            ResultSet rsHK = smtHK.executeQuery();
            while (rsHK.next()) {
                choice_hk.add(rsHK.getString("HKNK_HocKi"));
            }
            ResultSet rsNH = smtNH.executeQuery();
            while (rsNH.next()) {
                choice_nh.add(rsNH.getString("HKNK_NamHoc"));
            }
            ResultSet rsLop = stmLop.executeQuery();
            while (rsLop.next()) {
                choiceLop.add(rsLop.getString("LOP_Id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditHocPhan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showHOC_PHAN() {
//        PreparedStatement ps;
//        String show = "SELECT hp.HP_Id,HP_TenHocPhan,HKNK_NamHoc,HKNK_HocKi FROM `hoc_phan` hp JOIN `chi_tiet_giang_day` hk GROUP by HP_Id,HP_TenHocPhan,HKNK_NamHoc,HKNK_HocKi;";
        loadHOC_PHANs HPs = new loadHOC_PHANs();
        String hk;
        ArrayList<HOC_PHANs> listHOC_PHAN = new ArrayList<>();
        listHOC_PHAN = HPs.loadHocPhan(GV_Id);
        for (HOC_PHANs hOC_PHANs : listHOC_PHAN) {
            if (hOC_PHANs.getHKNK_HocKy() == null || hOC_PHANs.getHKNK_Nam() == null) {
                hk = "Chưa sắp xếp chi tiết giảng dạy";
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{hOC_PHANs.getHP_Id(), hOC_PHANs.getHP_TenHocPhan(), hk, hk, hk});
            } else {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{hOC_PHANs.getHP_Id(), hOC_PHANs.getHP_TenHocPhan(), hOC_PHANs.getHKNK_HocKy(), hOC_PHANs.getHKNK_Nam(), hOC_PHANs.getLop_Id()});
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

        jplDelete = new javax.swing.JPanel();
        btnDelete = new java.awt.Button();
        label2 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new java.awt.Label();
        tillte = new java.awt.Label();
        btnMinSreen = new java.awt.Label();
        HocPhan = new javax.swing.JButton();
        Cauhoi = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        input_mahocphan = new java.awt.TextField();
        choice_nh = new java.awt.Choice();
        choice_hk = new java.awt.Choice();
        input_hocphan = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_insert = new java.awt.Button();
        btn_cancel = new java.awt.Button();
        mess = new java.awt.Label();
        choiceLop = new java.awt.Choice();
        label1 = new java.awt.Label();

        btnDelete.setLabel("button1");

        javax.swing.GroupLayout jplDeleteLayout = new javax.swing.GroupLayout(jplDelete);
        jplDelete.setLayout(jplDeleteLayout);
        jplDeleteLayout.setHorizontalGroup(
            jplDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jplDeleteLayout.setVerticalGroup(
            jplDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        label2.setText("label2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(41, 47, 54));
        jPanel1.setForeground(new java.awt.Color(41, 47, 54));

        btnClose.setAlignment(java.awt.Label.CENTER);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("X");
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        tillte.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tillte.setForeground(new java.awt.Color(255, 255, 255));
        tillte.setText("HOME");

        btnMinSreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinSreen.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnMinSreen.setForeground(new java.awt.Color(255, 255, 255));
        btnMinSreen.setText("_");
        btnMinSreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinSreenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tillte, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinSreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinSreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnClose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tillte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        HocPhan.setText("Học Phần");

        Cauhoi.setText("Câu Hỏi");
        Cauhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CauhoiActionPerformed(evt);
            }
        });

        jButton3.setText("Tạo Đề");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setEnabled(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã học phần", "Tên học phần", "Học kỳ", "Năm học", "Lớp", "Tác vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jTable1.setSelectionBackground(new java.awt.Color(144, 175, 195));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Mã học phần");

        jLabel2.setText("Tên học phần");

        jLabel3.setText("Học kỳ");

        jLabel4.setText("Năm học");

        jLabel5.setText("Lớp");

        btn_insert.setLabel("Thêm");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_cancel.setLabel("Huỷ");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        mess.setAlignment(java.awt.Label.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input_mahocphan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(choiceLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(input_hocphan, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(choice_hk, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choice_nh, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(373, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(319, 319, 319))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input_mahocphan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(input_hocphan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(choice_hk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(choice_nh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(choiceLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_insert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label1.setText("Danh Mục Học Phần");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(HocPhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cauhoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(HocPhan)
                        .addComponent(Cauhoi)
                        .addComponent(jButton3))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinSreenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinSreenMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinSreenMouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        String mahocphan = input_mahocphan.getText();
        String tenhocphan = input_hocphan.getText();
        String namhoc = choice_nh.getSelectedItem();
        String hocky = choice_hk.getSelectedItem();
        String malop = choiceLop.getSelectedItem();

        if (mahocphan.isEmpty() || tenhocphan.isEmpty() || malop.isEmpty()) {
            mess.setAlignment((int) mess.CENTER);
            mess.setText("Nhập đầy đủ thông tin,Pls! ");
            mess.setForeground(Color.red);
        } else {
            mess.setText("");
            String insert_hocphan = "insert into hoc_phan values(?,?,?)";
            String insert_chitietgiangday = "insert into chi_tiet_giang_day values(?,?,?,?)";
            try {
                String checkHocPhan = "select HP_Id from Hoc_Phan where HP_Id = '"+mahocphan+"'";
                PreparedStatement stm_check = MySQLConnect.MySQLConnect().prepareStatement(checkHocPhan);
                ResultSet rs = stm_check.executeQuery();
                if (rs.next()) {
                    mess.setText(mahocphan + " đã tồn tại");
                    mess.setForeground(Color.red);
                } else {
                    PreparedStatement stmInsert_hocphan = MySQLConnect.MySQLConnect().prepareStatement(insert_hocphan);
                    stmInsert_hocphan.setString(1, mahocphan);
                    stmInsert_hocphan.setInt(2, GV_Id);
                    stmInsert_hocphan.setString(3, tenhocphan);
                    stmInsert_hocphan.execute();
                    PreparedStatement stmInsert_chitietgiangday = MySQLConnect.MySQLConnect().prepareStatement(insert_chitietgiangday);
                    stmInsert_chitietgiangday.setString(1, namhoc);
                    stmInsert_chitietgiangday.setString(2, hocky);
                    stmInsert_chitietgiangday.setString(3, mahocphan);
                    stmInsert_chitietgiangday.setString(4, malop);
                    stmInsert_chitietgiangday.execute();
                    JOptionPane.showMessageDialog(null, "Đã thêm thành công!");
                    Home home = new Home(GV_HoTen, GV_Id);
                    home.setVisible(true);
                    this.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
//         jmess.setVisible();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void CauhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CauhoiActionPerformed
        jcauhoi jch = new jcauhoi(GV_Id, GV_HoTen);
        jch.setVisible(true);
//        jch.pack();
        jch.setLocationRelativeTo(null);
//        jch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_CauhoiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TaoDeThi tdt = new TaoDeThi(GV_Id, GV_HoTen);
        tdt.setVisible(true);
        tdt.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home("", 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cauhoi;
    private javax.swing.JButton HocPhan;
    private java.awt.Label btnClose;
    private java.awt.Button btnDelete;
    private java.awt.Label btnMinSreen;
    private java.awt.Button btn_cancel;
    private java.awt.Button btn_insert;
    private java.awt.Choice choiceLop;
    private java.awt.Choice choice_hk;
    private java.awt.Choice choice_nh;
    private java.awt.TextField input_hocphan;
    private java.awt.TextField input_mahocphan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jplDelete;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label mess;
    private java.awt.Label tillte;
    // End of variables declaration//GEN-END:variables
}
