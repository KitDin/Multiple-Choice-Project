package MultipleChoiceCode;

import CauHoi.DeThi_CauHoi;
import CauHoi.LoadDeThi_CauHoi;
import CauHoi.LoadQuestions;
import CauHoi.Questions;
import HocPhan.loadHOC_PHANs;
import HocPhan.HOC_PHANs;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import static java.lang.Thread.sleep;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import java.sql.Time;

/**
 *
 * @author DELL
 */
public class NewClass {

    public static boolean checkSpecialChar(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.matches(".*[^A-Za-z0-9 ].*");
    }

    public static boolean containsSpecialCharacter(String str) {
        Pattern pattern = Pattern.compile(".*[^A-Za-z0-9 ].*");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static int maxCH_IDMYSQL(String HP_Id) {
        int a = 0;
        String maxCH = "select max(CH_Id) as id from cau_hoi where HP_Id='" + HP_Id + "'";
        PreparedStatement stm;
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(maxCH);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                a = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(jcauhoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

//    public static ArrayList<Questions> show_random_i_cauhoi(ArrayList<Questions> list_cau_hoi_de, ArrayList<Questions> list_cau_hoi_trungbinh, ArrayList<Questions> list_cau_hoi_kho, int de, int trungbinh, int kho) {
//
//        Collections.shuffle(list_cau_hoi_de);
//        Collections.shuffle(list_cau_hoi_trungbinh);
//        Collections.shuffle(list_cau_hoi_kho);
//
//        ArrayList<Questions> list_cau_hoi_da_tron_mucdo = new ArrayList<>();
//        for (int i = 0; i < de; i++) {
//            Questions a = list_cau_hoi_de.get(i);
//            list_cau_hoi_da_tron_mucdo.add(a);
//        }
//
//        for (int i = 0; i < trungbinh; i++) {
//            Questions b = list_cau_hoi_trungbinh.get(i);
//            list_cau_hoi_da_tron_mucdo.add(b);
//        }
//
//        if (kho != 0) {
//            for (int i = 0; i < kho; i++) {
//                Questions b = list_cau_hoi_kho.get(i);
//                list_cau_hoi_da_tron_mucdo.add(b);
//            }
//        }
//        Collections.shuffle(list_cau_hoi_da_tron_mucdo);
//
//        return list_cau_hoi_da_tron_mucdo;
//
//    }
    public static ArrayList<Integer> randomMucDoCauHoi(int DT_SoCau) {

        Random rand = new Random();
        int a, b, c;
        ArrayList<Integer> tong = new ArrayList<>();
        while (true) {
            a = rand.nextInt(0, DT_SoCau);
            b = rand.nextInt(0, DT_SoCau);
            c = rand.nextInt(0, DT_SoCau);
            if (a + b + c == 10 && c < a && c < b && b < a) {
                tong.add(0, a);
                tong.add(1, b);
                tong.add(2, c);
                return tong;
            }
        }
    }

    public static Time showTime() {
        String select_CauHoi = "SELECT DISTINCT d.DT_ThoiGianThi FROM de_thi d join chi_tiet_de_thi c on d.DT_Id=c.DT_Id where d.DT_Id=?;";
        PreparedStatement stm;
        Time a = new Time(0);
        try {
            stm = MySQLConnect.MySQLConnect().prepareStatement(select_CauHoi);
            stm.setInt(1, 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                a = rs.getTime(1);
            }
        } catch (Exception e) {
        }
        return a;
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<DeThi_CauHoi> dtcu = new LoadDeThi_CauHoi().loadDETHI_CAUHOI(2);
        for (DeThi_CauHoi deThi_CauHoi : dtcu) {
            System.out.println(deThi_CauHoi.getCH_Id());
        }
    }
}
