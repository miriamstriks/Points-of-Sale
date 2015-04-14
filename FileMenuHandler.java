package project370;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.*;


public class FileMenuHandler implements ActionListener{
   
   JFrame jframe;
   public static boolean isOn = false;
   public static JFrame form;

   public FileMenuHandler(JFrame jf) {
       jframe = jf;
   }

   @Override
   public void actionPerformed(ActionEvent event) {

       String buttonName = event.getActionCommand();

       // add new menu items
       if (buttonName.equals("Add Item"))
           addItem();
           
       // cancel button on the pop up windows
       else if (buttonName.equals("Cancel"))
           cancel();

       // remove item on main gui
       else if (buttonName.equals("Remove Item")) 
           removeItem();

       // add member on main gui
       else if (buttonName.equals("Add Member"))
           addMember();

       // remove member on main gui
       else if (buttonName.equals("Delete Member")) 
           deleteMember();

       // find member on main gui
       else if (buttonName.equals("Find Member")) 
           findMember();

       // import file on main gui
       else if (buttonName.equals("Import/Export File"))
           importExport();

       // sells report on main gui
       else if (buttonName.equals("Orders Report"))
           ordersReport();

       // PAY on main gui
       else if (buttonName.equals("PAY"))
           pay();
   }

   private void importExport() {
       formIsOn();
       tableIsOn();
       form = new ImportExportGUI();
       ImportExportGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);
   }


   private void pay() {
       // display a message if the orderList is empty
       if (GUI.orderList.getItemCount() == 0) {
           JOptionPane.showMessageDialog(null, "Your cart is empty");
           return;
       }

       ConnectDB dbconnect = new ConnectDB();

       // if the member id is input in the main gui
       // add the amount of money spent to rewards points
       // 1 reward point per dollar spent
       if (!GUI.memberid.getText().isEmpty()) {

           // get the current reward point and add it by the total amount spend
           try {

               // make sure the memberid is in interger form
               int memberID;
               try {
                   memberID = Integer.parseInt(GUI.memberid.getText());
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(null, "Invalid Member ID");
                   return;
               }

               // check if the memberid exists
               // prompt the user to reenter if not valid
               if (!Account.memberIdIsValid(memberID)) {
                  JOptionPane.showMessageDialog(null, "Invalid Member ID");
                  return;
               }

               dbconnect.pst = dbconnect.con.prepareStatement("select * from members where memberid="+memberID);
               dbconnect.rs = dbconnect.pst.executeQuery();

               int currentpoints = (int) Double.parseDouble(GUI.totalLabel.getText());
               int updatedPoints = 0;

               // update the value of the points
               while (dbconnect.rs.next()) {
                   updatedPoints = Integer.parseInt(dbconnect.rs.getString(9)) + currentpoints;
               }

               // update the new value into database
               dbconnect.pst = dbconnect.con.prepareStatement
                     ("update members set rewardpoints="+updatedPoints+ " where memberid="+memberID);
               dbconnect.pst.executeUpdate();

           } catch (SQLException e) {
               e.printStackTrace();
           }

       }

       // add the sells record into database
       try {
           dbconnect.pst = dbconnect.con.prepareStatement("insert into orders(sname,scost,sdate,username) values (?, ?,default, ?)");

           for (int i = 0; i < GUI.orderList.getItemCount(); i++) {
               String[] temp = GUI.orderList.getItem(i).split(" ");
               String name = temp[9].toUpperCase();

               // add taxes to the item before putting into database and limit the decimal to 2
               double value = Double.parseDouble(temp[1]) * 1.08875;
               DecimalFormat df = new DecimalFormat("0.00");
               String cost = "";
               cost += df.format(value);

               dbconnect.pst.setString(1, name);
               dbconnect.pst.setString(2, cost);
               dbconnect.pst.setString(3, Start.loginName);

               dbconnect.pst.executeUpdate();
           }

       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           dbconnect.close();
       }

       // after paid, reset the order list
       if (GUI.orderList.getItemCount() != 0) {
           JOptionPane.showMessageDialog(null, "THANK YOU!");
           GUI.orderList.removeAll();
           GUI.totalLabel.setText("0.00");
           GUI.taxLabel.setText("0.00");
       }

       GUI.memberid.setText("");
   }

   private void ordersReport() {
       formIsOn();
       tableIsOn();
       form = new OrdersReportGUI();
       OrdersReportGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);
   }

   private void deleteMember() {
       formIsOn();
       tableIsOn();
       form = new DeleteMemberGUI();
       DeleteMemberGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);
   }

   private void findMember() {
       formIsOn();
       tableIsOn();
       form = new SearchMembersGUI();
       SearchMembersGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);

   }

   private void addItem() {
       formIsOn();
       tableIsOn();
       form = new AddItemGUI();
       AddItemGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);
   }

   private void addMember() {
       formIsOn();
       tableIsOn();
       form = new AddMemberGUI();
       AddMemberGUI.isOn = true;
       form.setLocationRelativeTo(null);
       form.setVisible(true);
   }

   private void removeItem() {

       if (GUI.menuList.getItemCount() == 0)
           return;

       if (GUI.menuList.getSelectedIndex() == -1) {
           JOptionPane.showMessageDialog(null, "Please select an item to remove.");
           return;
       }

       // split the strings in the list
       String temp = GUI.menuList.getItem(GUI.menuList.getSelectedIndex());
       String[] values = temp.split(" ");

       double cost = Double.parseDouble(values[1]);

       String name = "";

       // get the menu item name
       for (int i = 2; i < values.length; i++) {
           if (!values[i].isEmpty()) {
               name += values[i] + " ";
           }
       }

       // remove the item from the database
       ConnectDB dbconnect = new ConnectDB();

       Account.accountInput();

       // prompt user to enter the username and password in order to remove
       // item
       if (!Account.isAdmin(Account.username, Account.password)) {
           dbconnect.close();
           return;
       }

       GUI.menuList.remove(GUI.menuList.getSelectedIndex());

       try {

           dbconnect.pst = dbconnect.con
                   .prepareStatement("delete from menu where mname=? && mcost=?");
           dbconnect.pst.setString(1, name);
           dbconnect.pst.setDouble(2, cost);
           dbconnect.pst.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           dbconnect.close();
       }
   }
   
   public static void tableIsOn() {
      if (ReportGUI.tableOn == true) {
          ReportGUI.fm.dispose();
      }
  }

   public static void formIsOn() {
       if (AddItemGUI.isOn || AddMemberGUI.isOn || DeleteMemberGUI.isOn || SearchMembersGUI.isOn
              || OrdersReportGUI.isOn || ImportExportGUI.isOn) 
           form.dispose();
   }

   private void cancel() {
       jframe.setVisible(false);
       isOn = false;
   }

   private void readSource(File chosenFile) {
       
       String error="The Following MemberID's were not added\n";
       try {
               
           Scanner in = new Scanner(chosenFile);
           ConnectDB dbconnect = new ConnectDB();

           while (in.hasNext()) {

               String memberid, firstname, lastname, address, city, state, zip, dob, rewardPoints;

               //get each line in the file and split using delimiter ,
               String temp = in.nextLine();
               String[] values = temp.split(",");
               
               memberid = values[0];
               firstname = values[1];
               lastname = values[2];
               address = values[3];
               city = values[4];
               state = values[5];
               zip = values[6];
               dob = values[7];
               rewardPoints = values[8];
               
               //insert the values into database
               try {
                   dbconnect.pst = dbconnect.con.prepareStatement
                         ("insert into members(memberid,firstname,lastname,address,city,state,zip,dob,rewardpoints) value(?,?,?,?,?,?,?,?,?)");
                   dbconnect.pst.setString(1, memberid);
                   dbconnect.pst.setString(2, firstname);
                   dbconnect.pst.setString(3, lastname);
                   dbconnect.pst.setString(4, address);
                   dbconnect.pst.setString(5, city);
                   dbconnect.pst.setString(6, state);
                   dbconnect.pst.setString(7, zip);
                   dbconnect.pst.setString(8, dob);
                   dbconnect.pst.setInt(9, Integer.parseInt(rewardPoints));
                   dbconnect.pst.executeUpdate();
                   dbconnect.close();
           
               } catch (SQLException e) {
                   error+=e.getMessage() + "\n";
               }
             in.close();
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       
       String chosenFileName = chosenFile.getName();
       JOptionPane.showMessageDialog(null, chosenFileName
               + " has been loaded " + "\n");
       
       //print out the member id that was not added 
       JOptionPane.showMessageDialog(null, error);
       
   }//method readfile

   private void openFile() {
       JFileChooser fileChooser = new JFileChooser();;
       int status = fileChooser.showOpenDialog(null);

       if (status == JFileChooser.APPROVE_OPTION) {
           readSource(fileChooser.getSelectedFile());
       }

       else
           JOptionPane.showMessageDialog(null, "Open file process canceled");

   } //method openFile

}//class FileMenuHandler
