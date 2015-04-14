package project370;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportExportGUI extends JFrame {
   private JPanel contentPane;
   public static boolean isOn = false;
   private FileMenuHandler fmh = new FileMenuHandler(this);

   /**
    * Create the frame.
    */
   public ImportExportGUI() {

       setType(Type.UTILITY);
       setResizable(false);
       setTitle("Import/Export Files");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setBounds(100, 100, 202, 197);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JButton importButton = new JButton("Import");
       importButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
       importButton.setBounds(34, 34, 117, 36);
       contentPane.add(importButton);
       
       importButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               openFile();
               setVisible(false);
           }
       });

       JButton exportButton = new JButton("Export");
       exportButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
       exportButton.setBounds(34, 87, 117, 36);
       contentPane.add(exportButton);
       exportButton.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent e) {
               outputfile();
               setVisible(false);
           }
       });
   }

   // export the member table in the database to a text file
   private void outputfile() {

       ConnectDB dbconnect = new ConnectDB();

       try {
           PrintWriter writer = new PrintWriter("memberoutput.txt");

           try {
               dbconnect.pst = dbconnect.con.prepareStatement("select * from members");
               dbconnect.rs = dbconnect.pst.executeQuery();

               while (dbconnect.rs.next()) {
                   writer.println(dbconnect.rs.getString(1) + ","
                           + dbconnect.rs.getString(2) + ","
                           + dbconnect.rs.getString(3) + ","
                           + dbconnect.rs.getString(4) + ","
                           + dbconnect.rs.getString(5) + ","
                           + dbconnect.rs.getString(6) + ","
                           + dbconnect.rs.getString(7) + ","
                           + dbconnect.rs.getString(8) + ","
                           + dbconnect.rs.getString(9));
               }

               writer.flush();
           } catch (SQLException e) {
               // TODO Auto-generated catch block
           }
           writer.close();

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       JOptionPane.showMessageDialog(null, "File exported");
   }

   private void readSource(File chosenFile) {
       
       String error = "";
       
       ConnectDB dbconnect = new ConnectDB();

       try {

           Scanner cin = new Scanner(chosenFile);

           while (cin.hasNext()) {

               String memberid, firstname, lastname, address, city, state, zip, dob, rewardPoints;

               // get each line in the file and split using delimiter ,
               String temp = cin.nextLine();
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

               // insert the values into database
               try {
                   dbconnect.pst = dbconnect.con
                           .prepareStatement("insert into members(memberid,firstname,lastname,address,city,state,zip,dob,rewardpoints) value(?,?,?,?,?,?,?,?,?)");
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

               } catch (SQLException e) {
                   error += e.getMessage() + "\n";
               }

           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       String chosenFileName = chosenFile.getName();
       JOptionPane.showMessageDialog(null, chosenFileName + " has been loaded " + "\n");

       // print out the member id that was not added
       if (!error.isEmpty()) {
           JOptionPane.showMessageDialog(null, "The Following MemberID are not added\n" + error);
       }
       dbconnect.close();
   }// readfile

   private void openFile() {
       JFileChooser chooser;
       int status;
       chooser = new JFileChooser();
       status = chooser.showOpenDialog(null);

       if (status == JFileChooser.APPROVE_OPTION) {
           readSource(chooser.getSelectedFile());
       }

       else
           JOptionPane.showMessageDialog(null, "Open File dialog canceled");

   } // openFile

}
