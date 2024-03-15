package CauHoi;

import java.sql.*;
import java.util.ArrayList;
import MultipleChoiceCode.MySQLConnect;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class LoadQuestions {

    public LoadQuestions(int GV_id) {
    }

    public ArrayList<Questions> loadQuestions(int a) {
        ArrayList<Questions> ListQuestions = new ArrayList<>();
        String sql = "SELECT * from cau_hoi c JOIN muc_do m on c.MD_Id=m.MD_Id join hoc_phan h on c.HP_id=h.HP_Id where  GV_Id=?;";
        PreparedStatement stm;
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(sql);
            stm.setInt(1, a);
            ResultSet rs = stm.executeQuery();
            int CH_id;
            String HP_Id, MD_TenMucDo, CH_CauHoi, CH_DapAn1, CH_DapAn2, CH_DapAn3, CH_DapAn4, DA_DapAnDung;
            while (rs.next()) {
                CH_id = rs.getInt("CH_Id");
                HP_Id = rs.getString("HP_Id");
                MD_TenMucDo = rs.getString("MD_Loai");
                CH_CauHoi = rs.getString("CH_CauHoi");
                CH_DapAn1 = rs.getString("CH_DapAn1");
                CH_DapAn2 = rs.getString("CH_DapAn2");
                CH_DapAn3 = rs.getString("CH_DapAn3");
                CH_DapAn4 = rs.getString("CH_DapAn4");
                DA_DapAnDung = rs.getString("DA_DapAnDung");
                Questions q = new Questions(CH_id, HP_Id, MD_TenMucDo, CH_CauHoi, CH_DapAn1, CH_DapAn2, CH_DapAn3, CH_DapAn4, DA_DapAnDung);
                ListQuestions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoadQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListQuestions;
    }
}
