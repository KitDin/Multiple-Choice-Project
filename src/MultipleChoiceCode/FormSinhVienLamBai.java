/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MultipleChoiceCode;

import java.awt.Color;
import javax.swing.JFrame;
import CauHoi.DeThi_CauHoi;
import CauHoi.LoadDeThi_CauHoi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.TimerTask;

/**
 *
 * @author DELL
 */
public class FormSinhVienLamBai extends javax.swing.JFrame {

    String HS_Id, HS_Ten;
    java.util.Timer timer = new java.util.Timer();
    private int socauhoi;
    private int Madethi = 0;
    private Time k = getTime();
    private boolean isButtonClicked = false;
    private int index = 0;
    private int h = k.getHours();
    private int m = k.getMinutes();
    private int s = k.getSeconds();
    private ArrayList<DeThi_CauHoi> listQuestion;
    public ArrayList<String> dapan = new ArrayList<>();
    FormSinhVienLamBai FSV = this;

    /**
     * Creates new form FormSinhVien
     */
    public FormSinhVienLamBai(String HS_Id, String HS_Ten, int madethi) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.HS_Id = HS_Id;
        this.HS_Ten = HS_Ten;
        Madethi = madethi;
        listQuestion = listQ();
        k = getTime();
        h = k.getHours();
        m = k.getMinutes();
        s = k.getSeconds();
        socauhoi = getSoCauHoi();
        setVisible(true);
        this.setLocationRelativeTo(null);
        showCard(index);
        DONGHO();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (index < socauhoi - 1) {
                    String getvalue = getSelectedButton();
                    if (dapan.size() > index) {
                        dapan.set(index, getvalue);
                    } else {
                        dapan.add(getvalue);
                    }

                    index++;
                    GroupBTN.clearSelection();
                    showCard(index);

                    // Nếu lựa chọn của câu hỏi trước đó đã được lưu thì chọn lại lựa chọn đó
                    if (dapan.size() > index && dapan.get(index) != null) {
                        String selectedAnswer = dapan.get(index);
                        for (Enumeration<AbstractButton> buttons = GroupBTN.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();
                            if (button.getText().trim().equalsIgnoreCase(selectedAnswer.trim())) {
                                button.setSelected(true);
                                break;
                            }
                        }
                    }
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
                    String getvalue = getSelectedButton();
                    if (dapan.size() > index) {
                        dapan.set(index, getvalue);
                    } else {
                        dapan.add(getvalue);
                    }
                    index--;

                    showCard(index);
                    if (dapan.get(index) == null) {
                        GroupBTN.clearSelection();
                    } else {
                        String selectedAnswer = dapan.get(index);
                        for (Enumeration<AbstractButton> buttons = GroupBTN.getElements(); buttons.hasMoreElements();) {
                            AbstractButton button = buttons.nextElement();
                            if (button.getText().trim().equalsIgnoreCase(selectedAnswer.trim())) {
                                button.setSelected(true);
                                break;
                            }
                        }
                    }
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

    public ArrayList<DeThi_CauHoi> listQ() {
        LoadDeThi_CauHoi loaddt = new LoadDeThi_CauHoi();
        ArrayList<DeThi_CauHoi> NoneRandomList = new ArrayList<>();
        NoneRandomList = loaddt.loadDETHI_CAUHOI(Madethi);
        return RandomQuestion(NoneRandomList);
    }

    public ArrayList<DeThi_CauHoi> RandomQuestion(ArrayList<DeThi_CauHoi> RandomListQuestion) {
        Collections.shuffle(RandomListQuestion);
        return RandomListQuestion;
    }

    public ArrayList<String> DapAnRanDom(DeThi_CauHoi q) {
        ArrayList<String> DA = new ArrayList<>();
        DA = q.Get4DapAnRandom();
        return DA;
    }

    public void showCard(int i) {
        socau.setText("Cau " + (index + 1) + " : ");
        DeThi_CauHoi q = listQuestion.get(i);
        lblQ1.setText(q.getCH_CauHoi().toUpperCase());
        A.setText(q.getCH_DapAn1());
        B.setText(q.getCH_DapAn2());
        C.setText(q.getCH_DapAn3());
        D.setText(q.getCH_DapAn4());
    }

    public Time getTime() {
        String select_CauHoi = "SELECT DISTINCT d.DT_ThoiGian FROM de_thi d join chi_tiet_de_thi c on d.DT_Id=c.DT_Id where d.DT_Id=?;";
        PreparedStatement stm;
        Time a = new Time(0);
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(select_CauHoi);
            stm.setInt(1, Madethi);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                a = rs.getTime(1);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public int getSoCauHoi() {
        String select_CauHoi = "SELECT DISTINCT DT_SoCau FROM de_thi where DT_Id=?;";
        PreparedStatement stm;
        int a = 0;
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(select_CauHoi);
            stm.setInt(1, Madethi);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                a = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public void DONGHO() {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if (s != 0) {
                    cd.setText(h + ":" + m + ":" + s);
                    s--;
                } else if (m > 0) {
                    cd.setText(h + ":" + m + ":" + s);
                    m--;
                    s = 60;
                } else if (h > 0) {
                    cd.setText(h + ":" + m + ":" + s);
                    h--;
                    s = 60;
                    m = 60;
                }

                if (h == 0 && m == 0 && s == 0) {
                    timer.cancel();
                    if (!isButtonClicked) {
                        String getvalue = getSelectedButton();
                        boolean isLastIndex = index == dapan.size() - 1;
                        if (isLastIndex) {
                            dapan.set(index, getvalue);
                        } else {
                            while (dapan.size() < socauhoi) {
                                dapan.add(null);
                            }
                            dapan.set(index, getvalue);
                        }
                        String stm = "INSERT INTO dap_an_da_lam values(?,?,?,?)";
                        for (int i = 0; i < dapan.size(); i++) {

                            DeThi_CauHoi c = listQuestion.get(i);
                            PreparedStatement stm_insertDapAn;
                            try {
                                stm_insertDapAn = MySQLConnect.MySQLConnect().prepareStatement(stm);
                                stm_insertDapAn.setString(1, HS_Id);
                                stm_insertDapAn.setInt(2, c.getCH_Id());
                                stm_insertDapAn.setInt(3, c.getDT_Id());
                                stm_insertDapAn.setString(4, dapan.get(i));
                                stm_insertDapAn.executeUpdate();
                                String update_trangthai = "update trang_thai set TT_TinhTrang = 2 where HS_Id = ? and DT_Id = ?";
                                PreparedStatement stm_update_trangthai = MySQLConnect.MySQLConnect().prepareStatement(update_trangthai);
                                stm_update_trangthai.setString(1, HS_Id);
                                stm_update_trangthai.setInt(2, Madethi);
                                stm_update_trangthai.executeUpdate();
                            } catch (SQLException ex) {
                                Logger.getLogger(FormSinhVienLamBai.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        TinhDiem();
                        JXemLaiCauHoiDalam jdl = new JXemLaiCauHoiDalam(HS_Id, HS_Ten, Madethi);
                        jdl.setVisible(true);
                        jdl.pack();
                        jdl.setLocationRelativeTo(null);
                        jdl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        FSV.dispose();
                    }
                }
            }
        }, 0, 1000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroupBTN = new javax.swing.ButtonGroup();
        panelA = new javax.swing.JPanel();
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
        jPanel2 = new javax.swing.JPanel();
        preButton = new java.awt.Button();
        nextButton = new java.awt.Button();
        jButton1 = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        cd = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelA.setLayout(new java.awt.CardLayout());

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

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(C, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
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

        jButton1.setText("Hoàn Thành");
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

        panel1.setBackground(new java.awt.Color(41, 47, 54));

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setLabelFor(cd);
        jLabel1.setText("Thời gian");

        cd.setAlignment(java.awt.Label.CENTER);
        cd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cd.setForeground(new java.awt.Color(255, 255, 255));
        cd.setText("label5");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cd, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        cd.getAccessibleContext().setAccessibleDescription("");

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

    private void preButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preButtonActionPerformed

    }//GEN-LAST:event_preButtonActionPerformed

    public String getSelectedButton() {
        for (Enumeration<AbstractButton> buttons = GroupBTN.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed

    }//GEN-LAST:event_nextButtonActionPerformed

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed

    }//GEN-LAST:event_BActionPerformed

    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed

    }//GEN-LAST:event_AActionPerformed

    private void DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DActionPerformed

    }//GEN-LAST:event_DActionPerformed

    private void CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CActionPerformed

    }//GEN-LAST:event_CActionPerformed

    private void jblContentAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jblContentAncestorAdded

    }//GEN-LAST:event_jblContentAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int option = JOptionPane.showConfirmDialog(
                new Home(),
                "Bạn muốn hoàn thành bài thi",
                "Thông báo ",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_NO_OPTION) {
            try {
                isButtonClicked = true;
                String getvalue = getSelectedButton();
                boolean isLastIndex = index == dapan.size() - 1;
                if (isLastIndex) {
                    dapan.set(index, getvalue);
                } else {
                    while (dapan.size() < socauhoi) {
                        dapan.add(null);
                    }
                    dapan.set(index, getvalue);
                }
                String stm = "INSERT INTO dap_an_da_lam values(?,?,?,?)";
                for (int i = 0; i < dapan.size(); i++) {
                    DeThi_CauHoi c = listQuestion.get(i);
                    PreparedStatement stm_insertDapAn;
                    try {
                        stm_insertDapAn = MySQLConnect.MySQLConnect().prepareStatement(stm);
                        stm_insertDapAn.setString(1, HS_Id);
                        stm_insertDapAn.setInt(2, c.getCH_Id());
                        stm_insertDapAn.setInt(3, c.getDT_Id());
                        stm_insertDapAn.setString(4, dapan.get(i));
                        stm_insertDapAn.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(FormSinhVienLamBai.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String update_trangthai = "update trang_thai set TT_TinhTrang = 2 where HS_Id = ? and DT_Id = ?";
                PreparedStatement stm_update_trangthai = MySQLConnect.MySQLConnect().prepareStatement(update_trangthai);
                stm_update_trangthai.setString(1, HS_Id);
                stm_update_trangthai.setInt(2, Madethi);
                stm_update_trangthai.executeUpdate();
                TinhDiem();
                JXemLaiCauHoiDalam jdl = new JXemLaiCauHoiDalam(HS_Id, HS_Ten, Madethi);
                jdl.setVisible(true);
                jdl.pack();
                jdl.setLocationRelativeTo(null);
                jdl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(FormSinhVienLamBai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void TinhDiem() {
        String tinhdiem = "{call check_exam_results(?,?)}";
        CallableStatement stm;

        try {
            stm = MySQLConnect.MySQLConnect().prepareCall(tinhdiem);
            stm.setInt(1, Madethi);
            stm.setString(2, HS_Id);
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(FormSinhVienLamBai.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            java.util.logging.Logger.getLogger(FormSinhVienLamBai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSinhVienLamBai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSinhVienLamBai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSinhVienLamBai.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormSinhVienLamBai fvs = new FormSinhVienLamBai("", "", 0);
                fvs.repaint();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A;
    private javax.swing.JRadioButton B;
    private javax.swing.JRadioButton C;
    private javax.swing.JRadioButton D;
    private javax.swing.ButtonGroup GroupBTN;
    private java.awt.Label cd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jblContent;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JTextArea lblQ1;
    private java.awt.Button nextButton;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPanel panelA;
    private java.awt.Button preButton;
    private java.awt.Label socau;
    // End of variables declaration//GEN-END:variables

    private static class FormSinhvien {

        public FormSinhvien() {
        }
    }
}
