package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AccountRepository {
    private Connection connection;
    public AccountRepository() {
        this.connection = ConnectionManager.getConnection();
    }
    public Account getAccount(String username, String password) {
        Account account = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                AccountType type = AccountType.valueOf(resultSet.getString("type"));
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                account = new Account(id, createdAt, updatedAt, username, password, type);
                return account;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }

        return account;
    }

    public Account getAccountById(int id) {
        Account account = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM accounts WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                AccountType type = AccountType.valueOf(resultSet.getString("type"));
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                account = new Account(id, createdAt, updatedAt, username, password, type);
                return account;
            }
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        }

        return account;
    }

}


