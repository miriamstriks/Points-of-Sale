package project370;

import java.sql.*;

public class CreateDB {
   
   public static Connection con = null;
   public static PreparedStatement pst = null;
   public static ResultSet rs = null;
   public static String dbName = "370project";

   // create database for first time use
   public CreateDB() {

       String url = "jdbc:mysql://localhost:3306/";
       String user = "root";
       String password = "root";

       try {
           con = DriverManager.getConnection(url, user, password);

           // set database name as 370project
           pst = con.prepareStatement("create database " + dbName);
           pst.executeUpdate();
           System.out.println("Database Created");

           url = "jdbc:mysql://localhost:3306/" + dbName;
           con = DriverManager.getConnection(url, user, password);

           // create table accounts inside 370project
           pst = con.prepareStatement("CREATE TABLE accounts "
                   + " (username VARCHAR(45) NOT NULL, "
                   + "password VARCHAR(45) NOT NULL , "
                   + "atype VARCHAR(45) NOT NULL , "
                   + " PRIMARY KEY (`username`) )");
           pst.executeUpdate();

           System.out.println("Account Table Created");

           // insert an super user for accounts
           pst = con
                   .prepareStatement("insert into accounts(username,password,atype) values('ROOT','ROOT', 'ADMIN')");
           pst.executeUpdate();
           System.out.println("Item inserted");

           // create table members inside 370project
           pst = con.prepareStatement("CREATE  TABLE members "
                   + " (memberid INT NOT NULL, "
                   + "firstname VARCHAR(45) NOT NULL, "
                   + "lastname VARCHAR(45) NOT NULL, "
                   + "address VARCHAR(45) NOT NULL, "
                   + "city VARCHAR(45) NOT NULL, "
                   + "state VARCHAR(45) NOT NULL, " + "zip INT NOT NULL, "
                   + "dob DATE NOT NULL, " + "rewardpoints INT NOT NULL, "
                   + " PRIMARY KEY (`memberid`) )");
           pst.executeUpdate();
           System.out.println("Member Table Created");

           // create table menu inside 370project
           pst = con.prepareStatement("CREATE  TABLE menu "
                   + " (mid INT NOT NULL AUTO_INCREMENT, "
                   + "mname VARCHAR(45) NOT NULL, "
                   + "mcost VARCHAR(45) NOT NULL, " + "PRIMARY KEY (`mid`) )");
           pst.executeUpdate();
           System.out.println("Menu Table Created");

           // create table sells inside 370project
           pst = con
                   .prepareStatement("CREATE  TABLE orders "
                           + " (sid INT NOT NULL AUTO_INCREMENT, "
                           + "sname VARCHAR(45) NOT NULL,"
                           + "scost VARCHAR(45) NOT NULL,"
                           + "sdate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                           + "username VARCHAR(45) NOT NULL,"
                           + "PRIMARY KEY (`sid`),"
                           + "FOREIGN KEY (username) REFERENCES accounts(username))" );
           pst.executeUpdate();
           System.out.println("Orders Table Created");

       } catch (SQLException e) {
           // TODO Auto-generated catch block
           // JOptionPane.showMessageDialog(null,
           // "Database not created or already existed");
       }

   }
}


