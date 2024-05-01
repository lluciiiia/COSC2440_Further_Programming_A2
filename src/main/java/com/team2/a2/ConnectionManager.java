package com.team2.a2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public static void initConnection() {
        try {
            connection = DatabaseUtil.getConnection();
            System.out.println("Connected to the database!");
        } catch (SQLException | IOException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
