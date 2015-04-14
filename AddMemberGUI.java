package project370;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddMemberGUI extends JFrame{
   private JPanel contentPane;
   private FileMenuHandler fmh = new FileMenuHandler(this);
   public static boolean isOn = false;
   
   //table gui for find member options
   public AddMemberGUI() {
       
       setType(Type.UTILITY);
       setResizable(false);
       setTitle("Add Member");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setBounds(100, 100, 454, 530);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JLabel memberIDLabel = new JLabel("MemberID:");
       memberIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       memberIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       memberIDLabel.setBounds(48, 35, 115, 34);
       contentPane.add(memberIDLabel);

       JLabel fNameLabel = new JLabel("First Name:");
       fNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       fNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       fNameLabel.setBounds(48, 80, 115, 34);
       contentPane.add(fNameLabel);

       JLabel lNameLabel = new JLabel("Last Name:");
       lNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       lNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lNameLabel.setBounds(48, 125, 115, 34);
       contentPane.add(lNameLabel);

       JLabel addressLabel = new JLabel("Address:");
       addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       addressLabel.setBounds(48, 170, 115, 34);
       contentPane.add(addressLabel);

       JLabel cityLabel = new JLabel("City:");
       cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       cityLabel.setBounds(48, 215, 115, 34);
       contentPane.add(cityLabel);

       JLabel stateLabel = new JLabel("State:");
       stateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       stateLabel.setBounds(48, 260, 115, 34);
       contentPane.add(stateLabel);

       JLabel zipLabel = new JLabel("ZipCode:");
       zipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       zipLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       zipLabel.setBounds(48, 305, 115, 34);
       contentPane.add(zipLabel);

       JLabel dobLabel = new JLabel("Date of Birth:");
       dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       dobLabel.setBounds(13, 350, 150, 34);
       contentPane.add(dobLabel);

       JLabel pointsLabel = new JLabel("Loyalty Points:");
       pointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       pointsLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       pointsLabel.setBounds(0, 395, 163, 34);
       contentPane.add(pointsLabel);

       // memberID field
       final JTextField textField = new JTextField();
       textField.setColumns(10);
       textField.setBounds(173, 35, 222, 34);
       contentPane.add(textField);

       // firstname field
       final JTextField textField_1 = new JTextField();
       textField_1.setColumns(10);
       textField_1.setBounds(173, 80, 222, 34);
       contentPane.add(textField_1);

       // last name field
       final JTextField textField_2 = new JTextField();
       textField_2.setColumns(10);
       textField_2.setBounds(173, 125, 222, 34);
       contentPane.add(textField_2);

       // address field
       final JTextField textField_3 = new JTextField();
       textField_3.setColumns(10);
       textField_3.setBounds(173, 170, 222, 34);
       contentPane.add(textField_3);

       // city field
       final JTextField textField_4 = new JTextField();
       textField_4.setColumns(10);
       textField_4.setBounds(173, 215, 222, 34);
       contentPane.add(textField_4);

       // state field
       final JTextField textField_5 = new JTextField();
       textField_5.setColumns(10);
       textField_5.setBounds(173, 260, 222, 34);
       contentPane.add(textField_5);

       // zip field
       final JTextField textField_6 = new JTextField();
       textField_6.setColumns(10);
       textField_6.setBounds(173, 305, 222, 34);
       contentPane.add(textField_6);

       // let user choose DOB
       // month choices
       final Choice monthChoice = new Choice();
       monthChoice.setBounds(173, 355, 65, 20);
       contentPane.add(monthChoice);
       for (int i = 1; i <= 9; i++) {
          monthChoice.add("0" + i);
       }
       monthChoice.add("10");
       monthChoice.add("11");
       monthChoice.add("12");

       // day choices
       final Choice dayChoice = new Choice();
       dayChoice.setBounds(244, 355, 65, 20);
       contentPane.add(dayChoice);
       for (int i = 1; i <= 9; i++) {
          dayChoice.add("0" + i);
       }

       for (int i = 10; i < 32; i++) {
          dayChoice.add(i + "");
       }

       // year choices
       final Choice yearChoice = new Choice();
       yearChoice.setBounds(315, 355, 80, 20);
       contentPane.add(yearChoice);
       for (int i = 2005; i >= 1925; i--) {
          yearChoice.add(i + "");
       }

       // loyalty points
       final JTextField textField_8 = new JTextField();
       textField_8.setColumns(10);
       textField_8.setBounds(173, 395, 222, 34);
       textField_8.setText("0");
       contentPane.add(textField_8);

       // Cancel for add member form
       JButton button_1 = new JButton("Cancel");
       button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
       button_1.setBounds(225, 450, 109, 38);
       contentPane.add(button_1);
       button_1.addActionListener(fmh);

       // add button for add member form
       JButton button = new JButton("Add");
       button.setFont(new Font("Tahoma", Font.PLAIN, 20));
       button.setBounds(106, 450, 109, 38);
       contentPane.add(button);
       button.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

               ConnectDB dbconnect = new ConnectDB();
               try {
                   // make sure all the information fields are populated
                   if (textField.getText().isEmpty()
                           || textField_1.getText().isEmpty()
                           || textField_2.getText().isEmpty()
                           || textField_3.getText().isEmpty()
                           || textField_4.getText().isEmpty()
                           || textField_5.getText().isEmpty()
                           || textField_6.getText().isEmpty()
                           || textField_8.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(null,"All information fields must be full.");
                       return;
                   }
                   // get the text from the member form and add into database
                   dbconnect.pst = dbconnect.con.prepareStatement("insert into  Members(memberID, firstname, lastname, address, city, state, zip, dob, rewardpoints) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

                   // make sure the number id is an integer
                   try {
                       dbconnect.pst.setInt(1, Integer.parseInt(textField.getText().toLowerCase()));
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null,"Invalid Member ID");
                       textField.requestFocus();
                       textField.selectAll();
                       return;
                   }

                   dbconnect.pst.setString(2, textField_1.getText().toUpperCase());
                   dbconnect.pst.setString(3, textField_2.getText().toUpperCase());
                   dbconnect.pst.setString(4, textField_3.getText().toUpperCase());
                   dbconnect.pst.setString(5, textField_4.getText().toUpperCase());
                   dbconnect.pst.setString(6, textField_5.getText().toUpperCase());
                   
                   //make sure zipcode is an integer
                   try {
                       dbconnect.pst.setInt(7, Integer.parseInt(textField_6.getText()));
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null,"Invalid Zip Code");
                       textField.requestFocus();
                       textField.selectAll();
                       return;
                   }             

                   String temp = monthChoice.getSelectedItem() + "/"
                           + dayChoice.getSelectedItem() + "/"
                           + yearChoice.getSelectedItem();

                   // format the input date into sql date format
                   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                   try {
                       java.sql.Date date = new java.sql.Date(sdf.parse(temp).getTime());
                       dbconnect.pst.setDate(8, date);
                   } catch (ParseException e1) {
                       JOptionPane.showMessageDialog(null, "Invalid DOB. Please enter as MM/DD/YYYY");
                   }
                   
                   //make sure loyalty points is an integer
                   try {
                       dbconnect.pst.setInt(9, Integer.parseInt(textField_8.getText()));
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null, "Invalid Rewards Points");
                       textField.requestFocus();
                       textField.selectAll();
                       return;
                   }

                   
                   dbconnect.pst.executeUpdate();

                   JOptionPane.showMessageDialog(null, "Member added");
                   setVisible(false);

               } catch (SQLException e1) {
                   JOptionPane
                           .showMessageDialog(null,"MemberID already exists. Please enter a new MemberID.");
                   textField.requestFocus();
                   textField.selectAll();
                   return;
               } finally {
                   dbconnect.close();
               }

           }
       });
   }

}
