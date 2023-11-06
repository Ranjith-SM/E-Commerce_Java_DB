package org.example.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

    private final static String dbcon = "jdbc:mysql://localhost:3306/ecommerce_java";
    private final static String username = "root";
    private final static String password = "root";

    public static Connection getconnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbcon,username,password);
//            System.out.println("Connection"+ !connection.isClosed());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
