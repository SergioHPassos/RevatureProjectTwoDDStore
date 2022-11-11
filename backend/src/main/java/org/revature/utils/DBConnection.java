package org.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // instance variables
    public static Connection connection;
    private static String url = "jdbc:postgresql://ec2-35-170-146-54.compute-1.amazonaws.com/d9lfkfb8q1efj7";
    private static String user = "xquekqkbufprxs";
    private static String password = "6adaf7b25ec1c3cb26c3e1c353a98a3455e7cd43a5f820a98fa852cd50ee03e4";

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
