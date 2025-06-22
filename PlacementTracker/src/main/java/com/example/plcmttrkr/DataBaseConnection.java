package com.example.plcmttrkr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String hostname = "database.c9ilt8h6arhp.eu-west-2.rds.amazonaws.com"; //AWS details
        String dbName = "PlacementUsers";
        String username = "admin";
        String password = "Group12db";
        int port = 3306;

        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?useSSL=false";

        try { //Try database connection
            //Class.forName("com.mysql.ch.jdbc.Driver");
            databaseLink = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Connection failure");
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}