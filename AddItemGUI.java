package project370;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddItemGUI extends JFrame {

   public static JTextField sfNameText;
   public static JTextField sfCostText;
   public static boolean isOn=false;
   private JPanel contentPane;
   private JButton btnAdd;
   private JButton btnCancel;
   private FileMenuHandler fmh = new FileMenuHandler(this);
   
   public AddItemGUI(){
       setType(Type.UTILITY);
       setResizable(false);
       setTitle("Add Item");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setBounds(100, 100, 321, 209);
       contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       JLabel lblNewLabel = new JLabel("Name: ");
       lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblNewLabel.setBounds(-20, 21, 90, 34);
       contentPane.add(lblNewLabel);

       JLabel lblNewLabel_1 = new JLabel("Cost: ");
       lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
       lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblNewLabel_1.setBounds(-20, 66, 90, 34);
       contentPane.add(lblNewLabel_1);

       sfNameText = new JTextField();
       sfNameText.setBounds(80, 21, 222, 34);
       contentPane.add(sfNameText);
       sfNameText.setColumns(10);

       sfCostText = new JTextField();
       sfCostText.setColumns(10);
       sfCostText.setBounds(80, 66, 222, 34);
       contentPane.add(sfCostText);

       //add button for add item
       btnAdd = new JButton("Add");
       btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
       btnAdd.setBounds(40, 122, 109, 38);
       contentPane.add(btnAdd);

       // Get the informations from the textfield and add it into the list
       btnAdd.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {

               String t;

               // check to see if the input is empty
               if (sfCostText.getText().isEmpty()
                       || sfNameText.getText().isEmpty()) {
                   JOptionPane.showMessageDialog(null,
                           "Missing Information, Please Re-Enter!");
                   return;
               }

               // try and catch to prevent entering invalid cost
               try {
                   double x = Double.parseDouble(sfCostText.getText());
                   DecimalFormat df = new DecimalFormat("0.00");
                   t = String.format("$ %-12s %s", df.format(x), sfNameText
                           .getText().toUpperCase());
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(null, "Invalid Cost!");
                   sfCostText.setText("");
                   return;
               }

               ConnectDB dbconnect = new ConnectDB();
               try {

                   Account.accountInput();

                   // check if the account is valid in the database
                   if (!Account.isAdmin(Account.username,
                           Account.password)) {
                       //dbconnect.close();
                       return;
                   }

                   // insert the values into database
                   dbconnect.pst = dbconnect.con
                           .prepareStatement("insert into  menu(mname,mcost) values (?, ?)");
                   dbconnect.pst.setString(1, sfNameText.getText()
                           .toUpperCase());

                   dbconnect.pst.setString(2, sfCostText.getText());
                   dbconnect.pst.executeUpdate();

                   // add the item to display on the GUI
                   GUI.menuList.add(t);
                   JOptionPane.showMessageDialog(null, "Item Added");
                   setVisible(false);

               } catch (SQLException e1) {
                   e1.printStackTrace();
               } finally {
                   dbconnect.close();
               }

           }
       });// Add button in the add menu springform

       // additem cancel Button
       btnCancel = new JButton("Cancel");
       btnCancel.addActionListener(fmh);
       btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       btnCancel.setBounds(159, 122, 109, 38);
       contentPane.add(btnCancel);
  }
   
}
