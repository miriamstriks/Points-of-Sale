package project370;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectDB {
   
   public static Connection con = null;
   public static PreparedStatement pst = null;
   public static ResultSet rs = null;

   public ConnectDB() {

       String url = "jdbc:mysql://localhost:3306/" + CreateDB.dbName;
       String user = "root";
       String password = "root";

       try {
           con = DriverManager.getConnection(url,user,password);
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           JOptionPane.showMessageDialog(null, "Database not connected");
       }

   }

   public void close() {

   try {
           if (rs != null) {
               rs.close();
           }

           if (pst != null) {
               pst.close();
           }

           if (con != null) {
               con.close();
           }
       } catch (Exception e) {

       }
   }

}



