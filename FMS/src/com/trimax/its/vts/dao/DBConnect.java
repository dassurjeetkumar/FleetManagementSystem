/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trimax.its.vts.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author admin
 */
public class DBConnect {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet Result;
    public boolean DEV = false;

    public DBConnect()
            throws IOException {
        Properties prop = new Properties();
        String path = "";
//        if (!DEV) {
//            path = "/opt/trimax/config/config.properties";
//        } else {
//            path = "D:/NetBeans/config/config.properties";
//        }
//        try {
//            FileInputStream fis = new FileInputStream(path);
//            prop.load(fis);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String URL = prop.getProperty("dbURL");
//
//        String User = prop.getProperty("dbUser");
//        String Password = prop.getProperty("dbPassword");
//        String URL = "jdbc:mysql://10.30.1.158:23306/D11";
//
//        String User = "temp";
//        String Password ="temp";
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            this.connection = DriverManager.getConnectioClass.forName("com.mysql.jdbc.Driver");n(URL, User, Password);
//            this.statement = this.connection.createStatement(1004, 1007);
//            this.statement.setQueryTimeout(30);
            //System.out.println("Connected"+this.connection);
        } catch (Exception e) { 
            e.printStackTrace();
        }
        
    }

    public void DBClose()
            throws SQLException {
       // this.Result.close();
        //this.statement.close();
       // sessionTimeOut() ;
        
        //this.connection.close();
        //System.out.println("Disconnected");
    }
    
    public void sessionTimeOut() throws SQLException {
        try {
          
            Statement statement = this.connection.createStatement(1004, 1007);
            //statement.executeUpdate("set session wait_timeout=45;");
            statement.executeUpdate("set session interactive_timeout=120;");
            statement.executeUpdate("set session wait_timeout=120;");
            ResultSet rs = statement.executeQuery("select 'timeout'");
            rs.close();
            statement.close();
           // this.connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
