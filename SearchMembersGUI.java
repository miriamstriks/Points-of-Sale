package project370;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SearchMembersGUI extends JFrame {
   
   private JPanel contentPane;
   private FileMenuHandler fmh = new FileMenuHandler(this);
   public static boolean isOn = false;

   public SearchMembersGUI() {
       setType(Type.UTILITY);
       setResizable(false);
       setTitle("Search Members");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       setBounds(100, 100, 402, 266);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JLabel lblFirstname = new JLabel("FirstName:");
       lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
       lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblFirstname.setBounds(10, 70, 111, 34);
       contentPane.add(lblFirstname);

       JLabel lblMemberid = new JLabel("MemberID:");
       lblMemberid.setHorizontalAlignment(SwingConstants.RIGHT);
       lblMemberid.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblMemberid.setBounds(10, 25, 111, 34);
       contentPane.add(lblMemberid);

       // memberid textfield
       final JTextField textField = new JTextField();
       textField.setColumns(10);
       textField.setBounds(131, 25, 222, 34);
       contentPane.add(textField);

       // firstname textfield
       final JTextField textField_1 = new JTextField();
       textField_1.setColumns(10);
       textField_1.setBounds(131, 70, 222, 34);
       contentPane.add(textField_1);

       JButton button_1 = new JButton("Cancel");
       button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
       button_1.setBounds(192, 179, 109, 38);
       contentPane.add(button_1);
       button_1.addActionListener(fmh);

       JLabel lblFirstname_1 = new JLabel("LastName:");
       lblFirstname_1.setHorizontalAlignment(SwingConstants.RIGHT);
       lblFirstname_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblFirstname_1.setBounds(10, 113, 111, 34);
       contentPane.add(lblFirstname_1);

       // lastname textfield
       final JTextField textField_2 = new JTextField();
       textField_2.setColumns(10);
       textField_2.setBounds(131, 115, 222, 34);
       contentPane.add(textField_2);

       JButton button = new JButton("Find");
       button.setFont(new Font("Tahoma", Font.PLAIN, 20));
       button.setBounds(73, 179, 109, 38);
       contentPane.add(button);
       button.addActionListener(new ActionListener() {

           public void actionPerformed(ActionEvent e) {

               if (textField.getText().isEmpty() && textField_1.getText().isEmpty() && textField_2.getText().isEmpty()) {
                   JOptionPane.showMessageDialog(null,"Error: All fields are empty");
                   return;
               }

               ConnectDB dbconnect = new ConnectDB();

               // search using member id first
               // if memberid is not inputted then use firstname
               // if theres no first name and member id use last name
               if (!textField.getText().isEmpty()) {
                   String memberid = textField.getText();
                   FileMenuHandler.tableIsOn();
                   ReportGUI.searchMember(memberid, "memberid");
               }

               // search using firstname
               else if (!textField_1.getText().isEmpty()) {
                   String firstname = textField_1.getText().toUpperCase();
                   FileMenuHandler.tableIsOn();
                   ReportGUI.searchMember(firstname, "firstname");
               } else if (!textField_2.getText().isEmpty()) {
                   String lastname = textField_2.getText().toUpperCase();
                   FileMenuHandler.tableIsOn();
                   ReportGUI.searchMember(lastname, "lastname");
               }
           }
       });
   }

}
