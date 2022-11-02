package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class atm_DB {
    private static Scanner ls = new Scanner(System.in);

    //new user
    public static boolean new_user(int id, int pass, String name) //new user check
    {
        boolean flag = false;

        try {
            Connection con = DatabaseConnection.DB_connect();

            Statement stm = con.createStatement();
            String sql = "select * from ATM where customer_ID= " + id + "";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Customer ID already exists.");
            } else {
                String q = "insert into ATM(customer_ID,customer_PIN,customer_Name, customer_balance) values(?,?,?, 0)";
                atm.USER_ID = id;
                //prepared statement
                con.prepareStatement(q);
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, id);
                ps.setInt(2, pass);
                ps.setString(3, name);

                //execute
                ps.executeUpdate();
                flag = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //existing user
    public static void existing_user(int id, int pass) {

        try {
            atm obj1 = new atm();//object created for atm
            Connection con = DatabaseConnection.DB_connect();

            Statement stm = con.createStatement();
            String sql = "select * from ATM where customer_ID= " + id + " and customer_PIN=" + pass + " ";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Login successful....");
                atm.USER_ID = id;
                obj1.lang();
            } else {
                System.out.println("User ID or Password Incorrect!!");
//                System.out.flush();
                obj1.user();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //pass change
    public static void pass_change(int pass, int id) {
        try {
            atm obj1 = new atm();//object created for atm
            Connection con = DatabaseConnection.DB_connect();

            Statement stm = con.createStatement();
            String sql = "select * from ATM where customer_PIN=" + pass + " and customer_ID=" + id + "";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                try {
                    boolean check = false;
                    System.out.println("Enter your new Password:");
                    int new_pass = ls.nextInt();
                    Statement es = con.createStatement();
                    String ls = "update ATM set  customer_PIN=" + new_pass + " where customer_ID= " + id + "";
                    ResultSet vs = es.executeQuery(ls);
                    if (vs.next()) {

                        System.out.println("something went wrong...");
                    } else {
                        System.out.println("Pin Change successfully......");
                        obj1.user();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Password OR ID Incorrect!!");
//                System.out.flush();
                obj1.lang();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deposit_cash()//deposit cash
    {
        try {
            Connection con = DatabaseConnection.DB_connect();

            Statement stm = con.createStatement();
            System.out.println("Enter your Amount to Deposit:");
            int deposit_amount = ls.nextInt();
            Statement es = con.createStatement();
            String ls = "update ATM set customer_balance = customer_balance + " + deposit_amount + " where customer_ID= " + atm.USER_ID + "";
            ResultSet vs = es.executeQuery(ls);
            System.out.println("Successful Transaction....");
            atm.display();

        } catch (Exception e) {
            System.out.println("Some problem depositing cash");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void balance_enquiry() {
        try {
            atm obj1 = new atm();//object created for atm
            Connection con = DatabaseConnection.DB_connect();
            Statement stm = con.createStatement();
            String sql = "select customer_balance from ATM where customer_ID= " + atm.USER_ID;
            ResultSet rs = stm.executeQuery(sql);
            System.out.println("The Balance is: ");
            rs.next();
            System.out.println(rs.getInt(1));
            atm.display();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cash_withdrawal() {
        try {
            Connection con = DatabaseConnection.DB_connect();

            Statement stm = con.createStatement();
            System.out.println("Enter your Amount to withdraw:");
            int withdraw_amount = ls.nextInt();
            Statement es = con.createStatement();
            String ls = "update ATM set customer_balance = customer_balance - " + withdraw_amount + " where customer_ID= " + atm.USER_ID + "";
            ResultSet vs = es.executeQuery(ls);
            System.out.println("Successful Transaction....");
            atm.display();

        } catch (Exception e) {
            System.out.println("Some problem withdrawing cash");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
//    public static boolean mini_statments()
//    {
//        boolean flag = false ;
//
//        try {
//            Connection con =  DatabaseConnection.DB_connect();
//            String q = "select * from ATM balance_enquirey;";
//            //prepared statement
//            con.prepareStatement(q);
//            Statement st = con.createStatement();
//            flag =true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return flag;
//    }
}
