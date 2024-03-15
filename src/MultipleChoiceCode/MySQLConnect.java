package MultipleChoiceCode;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect {
    public static Connection MySQLConnect(){
        Connection conn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/cauhoitracnghiem?" + "user=root");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
