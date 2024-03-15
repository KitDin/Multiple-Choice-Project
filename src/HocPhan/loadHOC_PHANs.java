/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HocPhan;

import HocPhan.HOC_PHANs;
import MultipleChoiceCode.Home;
import MultipleChoiceCode.MySQLConnect;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author DELL
 */
public class loadHOC_PHANs {
    
    public ArrayList<HOC_PHANs> loadHocPhan(int GV_Id){
        ArrayList<HOC_PHANs> listHOCPHANs = new ArrayList<>();
        String show="SELECT distinct hp.HP_Id,HP_TenHocPhan,HKNK_NamHoc,HKNK_HocKi,LOP_Id FROM `hoc_phan` hp left JOIN `chi_tiet_giang_day` hk on hp.HP_id=hk.HP_Id where GV_Id =? ;";       
        try {
            PreparedStatement stm = MySQLConnect.MySQLConnect().prepareStatement(show);
            stm.setInt(1, GV_Id);
            ResultSet rs = stm.executeQuery();
            String ID,Ten,Nam,HocKy,Lop;
            while(rs.next()){
                ID=rs.getString("HP_Id");
                Ten=rs.getString("HP_TenHocPhan");
                Nam=rs.getString("HKNK_NamHoc");
                HocKy=rs.getString("HKNK_HocKi");
                Lop=rs.getString("LOP_Id");
                HOC_PHANs HPs = new HOC_PHANs(ID, Ten, Nam, HocKy,Lop);
                listHOCPHANs.add(HPs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHOCPHANs;
    }
    
}
