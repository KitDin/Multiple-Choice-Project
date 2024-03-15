/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CauHoi;

import java.util.ArrayList;
import java.sql.*;
import MultipleChoiceCode.MySQLConnect;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class LoadDeThi_CauHoi {

    public LoadDeThi_CauHoi() {
    }

    ;
    
    public ArrayList<DeThi_CauHoi> loadDETHI_CAUHOI(int DT_Id) {
        ArrayList<DeThi_CauHoi> list = new ArrayList<>();
        String select_CauHoi = "select * from chi_tiet_de_thi ct join cau_hoi ch on ct.CH_Id=ch.CH_Id where DT_Id = ?";
        PreparedStatement stm;
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(select_CauHoi);
            stm.setInt(1, DT_Id);
            ResultSet rs = stm.executeQuery();
            int CH_id, DT;
            String HP_Id, MD_TenMucDo, CH_CauHoi, CH_DapAn1, CH_DapAn2, CH_DapAn3, CH_DapAn4, DA_DapAnDung;
            while (rs.next()) {
                DT = rs.getInt("DT_Id");
                CH_id = rs.getInt("CH_Id");
                HP_Id = rs.getString("HP_Id");
                MD_TenMucDo = rs.getString("MD_Id");
                CH_CauHoi = rs.getString("CH_CauHoi");
                CH_DapAn1 = rs.getString("CH_DapAn1");
                CH_DapAn2 = rs.getString("CH_DapAn2");
                CH_DapAn3 = rs.getString("CH_DapAn3");
                CH_DapAn4 = rs.getString("CH_DapAn4");
                ArrayList<String> dapan = new ArrayList<>();
                dapan.add(CH_DapAn1);
                dapan.add(CH_DapAn2);
                dapan.add(CH_DapAn3);
                dapan.add(CH_DapAn4);
                Collections.shuffle(dapan);
                DA_DapAnDung = rs.getString("DA_DapAnDung");
                DeThi_CauHoi q = new DeThi_CauHoi(DT, CH_id, HP_Id, MD_TenMucDo, CH_CauHoi, dapan.get(0), dapan.get(1), dapan.get(2), dapan.get(3), DA_DapAnDung);
                list.add(q);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoadDeThi_CauHoi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    
    
    
}
