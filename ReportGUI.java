package project370;

import java.awt.BorderLayout;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReportGUI extends JFrame{
   
   private static JPanel contentPane;
   private static JTable jtable;
   public static boolean tableOn = false;
   public static JFrame fm;

   public ReportGUI() {

       setType(Type.UTILITY);
       setLocationRelativeTo(null);
       setResizable(false);
       setBounds(100, 100, 1200, 600);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       tableOn = true;
       contentPane.setLayout(new BorderLayout(0, 0));

   }

   public static void searchMember(String x, String query) {
       ConnectDB dbconnect = new ConnectDB();
       try {

           dbconnect.pst = dbconnect.con.prepareStatement("select * from members where " + query + "=?");
           dbconnect.pst.setString(1, x);
           dbconnect.rs = dbconnect.pst.executeQuery();

           ResultSetMetaData meta = dbconnect.rs.getMetaData();

           String[] columnNames = new String[meta.getColumnCount()];

           for (int i = 0; i < meta.getColumnCount(); i++) {
               columnNames[i] = meta.getColumnName(i + 1);
           }

           // counts how many rows are in the table
           int count = 0;
           while (dbconnect.rs.next()) {
               count++;
           }

           Object[][] data = new Object[count][meta.getColumnCount()];

           int i = 0;
           for (dbconnect.rs.beforeFirst(); dbconnect.rs.next(); i++) {
               for (int j = 0; j < meta.getColumnCount(); j++) {
                   data[i][j] = new String(dbconnect.rs.getString(j + 1));
               }
           }

           fm = new ReportGUI();
           fm.setTitle("Find Members");
           DefaultTableModel dtm = new DefaultTableModel(data, columnNames);

           // puts the column name into the table
           jtable = new JTable(dtm);

           contentPane.add(new JScrollPane(jtable));
           fm.add(new JScrollPane(jtable));
           fm.setVisible(true);
           jtable.setVisible(true);
           fm.setLocationRelativeTo(null);

       } catch (SQLException e1) {
           e1.printStackTrace();
       } finally {
           dbconnect.close();
       }
   }//method searchMember
       
    // add stuff into the daily reports jframe window
       public static void searchDaily(String startDate, String endDate) {

           ConnectDB dbconnect = new ConnectDB();

           try {

               dbconnect.pst = dbconnect.con.prepareStatement("select * from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               ResultSetMetaData meta = dbconnect.rs.getMetaData();

               String[] columnNames = new String[meta.getColumnCount()];

               for (int i = 0; i < meta.getColumnCount(); i++) {
                   columnNames[i] = meta.getColumnName(i + 1);
               }

               // counts how many rows are in the table
               int count = 0;
               while (dbconnect.rs.next()) {
                   count++;
               }

               // stores the information into an Object array
               Object[][] data = new Object[count+2][meta.getColumnCount()];

               int i = 0;
               for (dbconnect.rs.beforeFirst(); dbconnect.rs.next(); i++) {
                   for (int j = 0; j < meta.getColumnCount(); j++) {
                       data[i][j] = new String(dbconnect.rs.getString(j + 1));
                   }
               }
               
               //gets the total value of sales made
               dbconnect.pst = dbconnect.con
                       .prepareStatement("select sum(scost) from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               data[count + 1][meta.getColumnCount() - 2] = new String(
                       "Total Sells:");
               while (dbconnect.rs.next()) {
                   data[count + 1][meta.getColumnCount() - 1] = dbconnect.rs
                           .getString(1);
               }

               fm = new ReportGUI();
               fm.setTitle("Sells Report by Daily");
               DefaultTableModel dtm = new DefaultTableModel(data, columnNames);

               // puts the column name into the table
               jtable = new JTable(dtm);

               contentPane.add(new JScrollPane(jtable));
               fm.add(new JScrollPane(jtable));
               fm.setVisible(true);
               jtable.setVisible(true);
               fm.setLocationRelativeTo(null);
               jtable.setVisible(true);
               dbconnect.close();

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }//method searchDaily

       // add stuff into the monthly reports jframe window
       public static void searchMonthly(String startDate, String endDate) {

           ConnectDB dbconnect = new ConnectDB();

           try {
               dbconnect.pst = dbconnect.con
                       .prepareStatement("select * from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               ResultSetMetaData meta = dbconnect.rs.getMetaData();

               String[] columnNames = new String[meta.getColumnCount()];

               for (int i = 0; i < meta.getColumnCount(); i++) {
                   columnNames[i] = meta.getColumnName(i + 1);
               }

               // counts how many rows are in the table
               int count = 0;
               while (dbconnect.rs.next()) {
                   count++;
               }

               // stores the information into an Object array
               Object[][] data = new Object[count+2][meta.getColumnCount()];

               int i = 0;
               for (dbconnect.rs.beforeFirst(); dbconnect.rs.next(); i++) {
                   for (int j = 0; j < meta.getColumnCount(); j++) {
                       data[i][j] = new String(dbconnect.rs.getString(j + 1));
                   }
               }
               
               //gets the total value of sales made
               dbconnect.pst = dbconnect.con
                       .prepareStatement("select sum(scost) from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               data[count + 1][meta.getColumnCount() - 2] = new String(
                       "Total Sells:");
               while (dbconnect.rs.next()) {
                   data[count + 1][meta.getColumnCount() - 1] = dbconnect.rs
                           .getString(1);
               }

               fm = new ReportGUI();
               fm.setTitle("Monthly Sales Report");
               DefaultTableModel dtm = new DefaultTableModel(data, columnNames);

               // puts the column name into the table
               jtable = new JTable(dtm);

               contentPane.add(new JScrollPane(jtable));
               fm.add(new JScrollPane(jtable));
               fm.setVisible(true);
               jtable.setVisible(true);
               fm.setLocationRelativeTo(null);
               jtable.setVisible(true);
               dbconnect.close();

           } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }

       }//method searchMonthly

       // add stuff into the yearly reports jframe window
       public static void searchYearly(String startDate, String endDate) {

           ConnectDB dbconnect = new ConnectDB();

           try {
               dbconnect.pst = dbconnect.con
                       .prepareStatement("select * from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               ResultSetMetaData meta = dbconnect.rs.getMetaData();

               String[] columnNames = new String[meta.getColumnCount()];

               for (int i = 0; i < meta.getColumnCount(); i++) {
                   columnNames[i] = meta.getColumnName(i + 1);
               }

               // counts how many rows are in the table
               int count = 0;
               while (dbconnect.rs.next()) {
                   count++;
               }

               // stores the information into an Object array
               Object[][] data = new Object[count + 2][meta.getColumnCount()];

               int i = 0;
               for (dbconnect.rs.beforeFirst(); dbconnect.rs.next(); i++) {
                   for (int j = 0; j < meta.getColumnCount(); j++) {
                       data[i][j] = new String(dbconnect.rs.getString(j + 1));
                   }
               }
               
               //gets the total value of sells made
               dbconnect.pst = dbconnect.con
                       .prepareStatement("select sum(scost) from sells where sdate>=? and sdate<=?");
               dbconnect.pst.setString(1, startDate);
               dbconnect.pst.setString(2, endDate);
               dbconnect.rs = dbconnect.pst.executeQuery();

               data[count + 1][meta.getColumnCount() - 2] = new String("Total Sales:");
               while (dbconnect.rs.next()) {
                   data[count + 1][meta.getColumnCount() - 1] = dbconnect.rs
                           .getString(1);
               }

               fm = new ReportGUI();
               fm.setTitle("Yearly Sales Report");
               DefaultTableModel dtm = new DefaultTableModel(data, columnNames);

               // puts the column name into the table
               jtable = new JTable(dtm);
               contentPane.add(new JScrollPane(jtable));
               fm.add(new JScrollPane(jtable));
               fm.setVisible(true);
               jtable.setVisible(true);
               fm.setLocationRelativeTo(null);
               jtable.setVisible(true);
               dbconnect.close();

           } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }//method searchYearly
}
