package project370;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Account {
   public static String username=null;
   public static String password=null;
   //private static ConnectDB dbconnect;
   
   public static void accountInput(){
       
       String user, pw;
       
       user = JOptionPane.showInputDialog("Enter Your Username");

       while (user.isEmpty()) {
           user = JOptionPane.showInputDialog("Enter Your Username");
       }

       pw = JOptionPane.showInputDialog("Enter your Password");

       while (pw.isEmpty()) {
           pw = JOptionPane.showInputDialog("Enter Your Password");
       }
       
       username = user.toUpperCase();
       password = pw.toUpperCase();
   }
   
   //checks if the username and password match
   public static boolean isValid(String user, String pw) {

       boolean found = false;

       // prompt the user to enter username and password to add
       // item into menu
       // check if the username is in the database
       ConnectDB dbconnect = new ConnectDB();
       try {

           dbconnect.pst = dbconnect.con
                   .prepareStatement("select * from accounts where username=?");
           dbconnect.pst.setString(1, user);
           dbconnect.rs = dbconnect.pst.executeQuery();

           //check if both the username and password matches
           while(dbconnect.rs.next()){
               if(dbconnect.rs.getString(1).equals(user) && dbconnect.rs.getString(2).equals(pw))
                   found = true;
           }
           
       } catch (SQLException e) {
              e.printStackTrace();
       }
       
       if (!found) {
           JOptionPane.showMessageDialog(null, "Invalid Username or Password");
           return false;
       }
       else{
           return true;
       }
   }
   
   //check to see if the account is an Admin account type
   public static boolean isAdmin(String user, String pw) {
       
       boolean found = false;

       // prompt the user to enter username and password to add
       // item into menu
       // check if the username is in the database
       ConnectDB dbconnect = new ConnectDB();
       try {

           dbconnect.pst = dbconnect.con
                   .prepareStatement("select * from accounts where username=? and password=? and atype=?");
           dbconnect.pst.setString(1, user);
           dbconnect.pst.setString(2, pw);
           dbconnect.pst.setString(3, "ADMIN");
           dbconnect.rs = dbconnect.pst.executeQuery();
           
           //if there is result return then it is an admin account
           found = dbconnect.rs.next();
           
       } catch (SQLException e) {
              e.printStackTrace();
       }
               
       if (!found) {
           JOptionPane.showMessageDialog(null, "Invalid Username/Password or not an Administrative Account!");
           return false;
       }
       else{
           return true;
       }
       
   }
   
   //check if the account exist in the database
   public static boolean accountExist(String user) {

       boolean found = false;

       // prompt the user to enter username and password to add
       // item into menu

       ConnectDB dbconnect = new ConnectDB();

       // check if the username is in the database
       try {
           dbconnect.pst = dbconnect.con
                   .prepareStatement("select * from accounts where username=?");
           dbconnect.pst.setString(1, user);
           dbconnect.rs = dbconnect.pst.executeQuery();

           //check if both the username and password matches
           while(dbconnect.rs.next()){
               if(dbconnect.rs.getString(1).equals(user))
                   found = true;
           }
           
       } catch (SQLException e) {
             e.printStackTrace();
       }

       if (!found) {
           return false;
       }
       else{
           return true;
       }
   }
   

   // check if the memberid is found in the database
   public static boolean memberIdIsValid(int memberid) {

       boolean found = false;
       ConnectDB dbconnect = new ConnectDB();
       try {
           
           dbconnect.pst = dbconnect.con
                   .prepareStatement("select * from members where memberid=?");
           dbconnect.pst.setInt(1, memberid);
           dbconnect.rs = dbconnect.pst.executeQuery();

           // check if the resultset returned from MYSQL is empty or not
           found = dbconnect.rs.next();

       } catch (SQLException e) {
             e.printStackTrace();
       }

       if (found) {
           return true;
       }

       else {
           JOptionPane.showMessageDialog(null, "Invalid Member ID!");
           GUI.memberid.requestFocus();
           GUI.memberid.selectAll();
           return false;
       }
   }
}
