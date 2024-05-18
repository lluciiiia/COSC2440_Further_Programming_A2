package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;

import java.sql.*;

public class LogRepository {

    private Connection connection;

    public LogRepository() {
        this.connection = ConnectionManager.getConnection();
    }


    public void createLog(int accountId, String content) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO logs (account_id, content) " +
                    "VALUES (?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, accountId);
            statement.setString(2, content);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new log was inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error creating log: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }
}
