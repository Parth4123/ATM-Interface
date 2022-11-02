package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DatabaseConnection {
    static String userName, password, url, driver;
    static Connection con;
    static Statement st;

    public static Connection DB_connect() {
        userName = "Parth";
        password = "Parth4123";
        url = "jdbc:mariadb://localhost:3306/ATM";
        driver = "org.mariadb.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            st = con.createStatement();
            System.out.println("Connection is successful....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}