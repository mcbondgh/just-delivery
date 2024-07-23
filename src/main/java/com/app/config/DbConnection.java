package com.app.config;

import java.sql.*;

import static com.app.config.DbProperties.loadProps;

public class DbConnection {

    private Connection connection;
    protected PreparedStatement prepare;
    protected ResultSet resultSet;

    protected Connection getCon(){
        try {
            String URL = loadProps().getProperty("datasource.url");
            String USERNAME = loadProps().getProperty("datasource.username");
            String PASSWORD = loadProps().getProperty("datasource.password");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // connection.setAutoCommit(false);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }
        return connection;
    }

//     public static void main(String[] args) {
//         DbConnection coon = new DbConnection();
//         try {
//            coon.getCon();
//             System.out.println("connected");
//         } catch (Exception e) {
//             e.printStackTrace();
//             // TODO: handle exception
//         }
//     }

}
