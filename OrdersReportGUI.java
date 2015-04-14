package project370;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrdersReportGUI extends JFrame {
   
   private JPanel contentPane;
   private FileMenuHandler fmh = new FileMenuHandler(this);
   public static boolean isOn = false;

   public OrdersReportGUI() {
       setType(Type.UTILITY);
       setResizable(false);
       setTitle("Orders Report");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setBounds(100, 100, 328, 189);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JLabel lblSearchBy = new JLabel("Select a Date");
       lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 25));
       lblSearchBy.setHorizontalAlignment(SwingConstants.CENTER);
       lblSearchBy.setBounds(42, 6, 222, 33);
       contentPane.add(lblSearchBy);

       final Choice choice = new Choice();
       choice.setFont(new Font("Dialog", Font.PLAIN, 15));
       choice.setBounds(10, 70, 93, 28);
       contentPane.add(choice);
       for (int i = 1; i <= 9; i++) {
           choice.add("0" + i);
       }
       choice.add("10");
       choice.add("11");
       choice.add("12");

       final Choice choice_1 = new Choice();
       choice_1.setFont(new Font("Dialog", Font.PLAIN, 15));
       choice_1.setBounds(113, 70, 93, 28);
       contentPane.add(choice_1);
       for (int i = 1; i <= 9; i++) {
           choice_1.add("0" + i);
       }
       for (int i = 10; i <= 31; i++) {
           choice_1.add(i + "");
       }

       final Choice choice_2 = new Choice();
       choice_2.setFont(new Font("Dialog", Font.PLAIN, 15));
       choice_2.setBounds(219, 70, 93, 28);
       contentPane.add(choice_2);
       for (int i = 2013; i <= 2020; i++) {
           choice_2.add(i + "");
       }

       JLabel lblMonth = new JLabel("Month");
       lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 15));
       lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
       lblMonth.setBounds(22, 50, 70, 14);
       contentPane.add(lblMonth);

       JLabel lblDay = new JLabel("Day");
       lblDay.setFont(new Font("Tahoma", Font.PLAIN, 15));
       lblDay.setBounds(139, 50, 46, 14);
       contentPane.add(lblDay);

       JLabel lblYear = new JLabel("Year");
       lblYear.setHorizontalAlignment(SwingConstants.CENTER);
       lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
       lblYear.setBounds(226, 50, 70, 14);
       contentPane.add(lblYear);

       JButton btnDaily = new JButton("DAILY");
       btnDaily.setBounds(10, 119, 93, 42);
       contentPane.add(btnDaily);

       // shows the table for daily sells report
       btnDaily.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

               String month = choice.getSelectedItem();
               String day = choice_1.getSelectedItem();
               String year = choice_2.getSelectedItem();
               String date = year + "-" + month + "-" + day + " 00:00:00";
               String date2 = year + "-" + month + "-" + day + " 23:59:59";
               FileMenuHandler.tableIsOn();
               ReportGUI.searchDaily(date, date2);

           }
       });

       JButton btnMonthly = new JButton("MONTHLY");
       btnMonthly.setBounds(113, 119, 93, 42);
       contentPane.add(btnMonthly);

       // shows the table for monthly sells report
       btnMonthly.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

               String month = choice.getSelectedItem();
               String year = choice_2.getSelectedItem();
               String date = year + "-" + month + "-01 00:00:00";
               String date2 = year + "-" + month + "-31 23:59:59";
               FileMenuHandler.tableIsOn();
               ReportGUI.searchMonthly(date, date2);

           }
       });

       JButton btnYearly = new JButton("YEARLY");
       btnYearly.setBounds(219, 119, 93, 42);
       contentPane.add(btnYearly);
       btnYearly.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String year = choice_2.getSelectedItem();
               String date = year + "-01-01 00:00:00";
               String date2 = year + "-12-31 23:59:59";
               
               FileMenuHandler.tableIsOn();
               ReportGUI.searchYearly(date, date2);
               
           }
       });

   }

}
