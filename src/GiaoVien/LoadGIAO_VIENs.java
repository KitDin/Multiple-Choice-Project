/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GiaoVien;

import GiaoVien.GIAO_VIENs;
import MultipleChoiceCode.MySQLConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class LoadGIAO_VIENs {
    public ArrayList<GIAO_VIENs> loadGIAO_VIEN(){
        ArrayList<GIAO_VIENs> ListGIAO_VIEN = new ArrayList<>();
        try {          
            Statement stm = MySQLConnect.MySQLConnect().createStatement() ;
            ResultSet rs = stm.executeQuery("select * from GIAO_VIEN");
            int GV_Id;
            String GV_Hoten, GV_Taikhoan, GV_Matkhau;
            while(rs.next()){
                GV_Id = Integer.parseInt(rs.getString("GV_Id"));
                GV_Hoten = rs.getString("GV_Hoten");
                GV_Taikhoan = rs.getString("GV_Taikhoan");
                GV_Matkhau= rs.getString("GV_Matkhau");
                GIAO_VIENs GVs = new GIAO_VIENs(GV_Id, GV_Hoten, GV_Taikhoan, GV_Matkhau);
               ListGIAO_VIEN.add(GVs);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListGIAO_VIEN;
    }
}
