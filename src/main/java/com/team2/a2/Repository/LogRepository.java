package com.team2.a2.Repository;

/**
 * @author <Team 2>
 */

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Log> getLogsByAccountId(int accountId) {
        List<Log> logs = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT id, content, created_at FROM logs WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Timestamp timestamp = resultSet.getTimestamp("created_at");
                Date createdAt = new Date(timestamp.getTime());

                Log log = new Log(id, accountId, content, createdAt);
                logs.add(log);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching logs: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }

        return logs;
    }
}
