package project370;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DeleteMemberGUI extends JFrame {
   
   private JPanel contentPane;
   private FileMenuHandler fmh = new FileMenuHandler(this);
   public static boolean isOn = false;
   
   public DeleteMemberGUI(){
           setType(Type.UTILITY);
           setResizable(false);
           setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           setBounds(100, 100, 374, 209);
           contentPane = new JPanel();
           setTitle("Delete Member");
           contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
           setContentPane(contentPane);
           contentPane.setLayout(null);

           JLabel lblMemberid = new JLabel("MemberID:");
           lblMemberid.setHorizontalAlignment(SwingConstants.RIGHT);
           lblMemberid.setFont(new Font("Tahoma", Font.PLAIN, 20));
           lblMemberid.setBounds(10, 40, 109, 34);
           contentPane.add(lblMemberid);

           final JTextField textField = new JTextField();
           textField.setColumns(10);
           textField.setBounds(129, 40, 222, 34);
           contentPane.add(textField);

           JButton btnRemove = new JButton("Remove");
           btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
           btnRemove.setBounds(66, 103, 109, 38);
           contentPane.add(btnRemove);

           // remove button listener under remove member
           btnRemove.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (textField.getText().isEmpty()) {
                       JOptionPane.showMessageDialog(null,"Please enter a Member ID");
                       return;
                   }

                   ConnectDB dbconnect = new ConnectDB();
                   int memberid;

                   // make sure member id is an integer
                   try {
                       memberid = Integer.parseInt(textField.getText());
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null,"Please enter a valid Member ID");
                       textField.selectAll();
                       return;
                   }

                   Account.accountInput();

                   if (!Account.isAdmin(Account.username,Account.password)) {
                       dbconnect.close();
                       return;
                   }

                   try {

                       dbconnect.pst = dbconnect.con.prepareStatement("delete from members where memberid="+memberid);
                       dbconnect.pst.executeUpdate();
                       JOptionPane.showMessageDialog(null, "Member removed");
                       setVisible(false);
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   } finally {
                       dbconnect.close();
                   }
               }
           });

           JButton cancelButton = new JButton("Cancel");
           cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
           cancelButton.setBounds(185, 103, 109, 38);
           contentPane.add(cancelButton);
           cancelButton.addActionListener(fmh);
       }
}
