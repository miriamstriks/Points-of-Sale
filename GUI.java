package project370;

import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

public class GUI extends JFrame {
   public static List menuList;
   public static List orderList;
   public static JLabel totalLabel;
   public static JLabel taxLabel;
   public static JTextField memberid;

   private JPanel contentPane;
   private JPanel panel;
   private JLabel lblNewLabel;
   private JLabel lblSells;
   private JButton btnNewButton;
   private JButton btnFindExistingMember;
   private JButton btnSellsRecord;
   private JButton btnRemoveMember;
   private JButton btnPay;
   private JButton btnAddItem;
   private JButton btnRemoveItem;
   private JButton btnImport;
   private JButton btnCreateUser;
   private JLabel lblTax;

   /**
    * Create the frame.
    */
   public GUI() {

       setResizable(false);

       FileMenuHandler fmh = new FileMenuHandler(this);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(100, 100, 750, 747);
       contentPane = new JPanel();
       setTitle("CHECKOUT");
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       setContentPane(contentPane);
       contentPane.setLayout(null);

       panel = new JPanel();
       panel.setBounds(364, 0, 370, 500);
       contentPane.add(panel);
       panel.setLayout(null);

       lblSells = new JLabel("CART");
       lblSells.setHorizontalAlignment(SwingConstants.CENTER);
       lblSells.setFont(new Font("Arial", Font.PLAIN, 70));
       lblSells.setBounds(0, 11, 370, 65);
       panel.add(lblSells);

       JLabel lblTotalPay = new JLabel("TOTAL:");
       lblTotalPay.setFont(new Font("Tahoma", Font.PLAIN, 43));
       lblTotalPay.setHorizontalAlignment(SwingConstants.CENTER);
       lblTotalPay.setBounds(0, 460, 166, 40);
       panel.add(lblTotalPay);

       // total number field
       totalLabel = new JLabel("0.00");
       totalLabel.setBounds(158, 460, 185, 43);
       totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 43));
       totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       panel.add(totalLabel);

       JPanel panel_1 = new JPanel();
       panel_1.setBounds(0, 0, 364, 500);
       contentPane.add(panel_1);
       panel_1.setLayout(null);

       lblNewLabel = new JLabel("MENU");
       lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 70));
       lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
       lblNewLabel.setBounds(0, 11, 364, 65);
       panel_1.add(lblNewLabel);

       // button remove item
       btnRemoveItem = new JButton("Remove Item");
       btnRemoveItem.setBounds(182, 457, 182, 43);
       panel_1.add(btnRemoveItem);
       btnRemoveItem.addActionListener(fmh);

       // button add item
       btnAddItem = new JButton("Add Item");
       btnAddItem.setBounds(0, 457, 182, 43);
       panel_1.add(btnAddItem);
       btnAddItem.addActionListener(fmh);

       // menu list
       menuList = new List();
       menuList.setBounds(0, 88, 364, 370);
       menuList.setFont(new Font("Arial", Font.PLAIN, 25));
       panel_1.add(menuList);
       MenuListener ml = new MenuListener(menuList);
       menuList.addItemListener(ml);

       // sales list
       orderList = new List();
       orderList.setBounds(0, 88, 370, 326);
       orderList.setFont(new Font("Arial", Font.PLAIN, 25));
       panel.add(orderList);
       OrderListener ol = new OrderListener(orderList);
       orderList.addItemListener(ol);

       lblTax = new JLabel("TAX:");
       lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
       lblTax.setFont(new Font("Tahoma", Font.PLAIN, 43));
       lblTax.setBounds(49, 420, 104, 40);
       panel.add(lblTax);

       // tax number field
       taxLabel = new JLabel("0.00");
       taxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
       taxLabel.setFont(new Font("Tahoma", Font.PLAIN, 43));
       taxLabel.setBounds(158, 417, 185, 43);
       panel.add(taxLabel);
       ;

       btnNewButton = new JButton("Add Member");
       btnNewButton.setBounds(35, 511, 133, 43);
       contentPane.add(btnNewButton);
       btnNewButton.addActionListener(fmh);

       // find member button
       btnFindExistingMember = new JButton("Find Member");
       btnFindExistingMember.setBounds(35, 611, 133, 43);
       contentPane.add(btnFindExistingMember);
       btnFindExistingMember.addActionListener(fmh);

       // remove member button
       btnRemoveMember = new JButton("Remove Member");
       btnRemoveMember.setBounds(35, 561, 133, 43);
       contentPane.add(btnRemoveMember);
       btnRemoveMember.addActionListener(fmh);

       // pay button in main gui
       btnPay = new JButton("PAY");
       btnPay.setFont(new Font("Arial", Font.PLAIN, 75));
       btnPay.setBounds(383, 611, 328, 93);
       contentPane.add(btnPay);
       btnPay.addActionListener(fmh);

       // orders report button
       btnSellsRecord = new JButton("Orders Report");
       btnSellsRecord.setBounds(200, 511, 133, 43);
       contentPane.add(btnSellsRecord);
       btnSellsRecord.addActionListener(fmh);

       // import file button
       btnImport = new JButton("Import/Export File");
       btnImport.setBounds(200, 561, 133, 43);
       contentPane.add(btnImport);
       btnImport.addActionListener(fmh);

       // create user button
       btnCreateUser = new JButton("Create Users");
       btnCreateUser.setBounds(200, 611, 133, 43);
       contentPane.add(btnCreateUser);
       btnCreateUser.addActionListener(fmh);

       // member id text field
       memberid = new JTextField();
       memberid.setFont(new Font("Tahoma", Font.PLAIN, 20));
       memberid.setBounds(383, 559, 328, 47);
       contentPane.add(memberid);
       memberid.setColumns(10);

       JLabel lblMemberid = new JLabel("MemberID");
       lblMemberid.setHorizontalAlignment(SwingConstants.CENTER);
       lblMemberid.setFont(new Font("Tahoma", Font.PLAIN, 40));
       lblMemberid.setBounds(383, 511, 328, 43);
       contentPane.add(lblMemberid);

   }
}