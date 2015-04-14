package project370;

import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Start {
       
    public static String loginName=null;
    public static String loginPassword=null;
    
    // add the current menu item into the menu list
    public static void starts() {

        ConnectDB dbconnect = new ConnectDB();
        
        while (loginName==null||loginName.isEmpty()) {
            loginName = JOptionPane.showInputDialog("Enter Your Username");
        }

        while (loginPassword==null||loginPassword.isEmpty()) {
            loginPassword = JOptionPane.showInputDialog("Enter Your Password");
        }
        
        loginName = loginName.toUpperCase();
        loginPassword = loginName.toUpperCase();
        
        if(!Account.isValid(loginName,loginPassword)){
            dbconnect.close();
            System.exit(1);
            return;
        }         
        
        try {
            
            dbconnect.pst = dbconnect.con.prepareStatement("select * from menu");
            dbconnect.rs = dbconnect.pst.executeQuery();

            while (dbconnect.rs.next()) {
                double cost = dbconnect.rs.getDouble(3);
                String name = dbconnect.rs.getString(2);
                DecimalFormat df = new DecimalFormat("0.00");
                
                String t = String.format("$ %-12s %s", df.format(cost), name);
                GUI.menuList.add(t);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

