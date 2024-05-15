package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Request.UpdateAccountRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return account;
    }
  
    public Account createAccount(String username, String password, AccountType type) {
        Account account = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "INSERT INTO accounts (username, password, type) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setObject(3, type, Types.OTHER);

            // Execute the insert operation
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                // If insertion successful, retrieve the newly created account's ID
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    AccountType accountType = AccountType.valueOf(resultSet.getString("type"));
                    Date createdAt = resultSet.getDate("created_at");
                    Date updatedAt = resultSet.getDate("updated_at");
                    account = new Account(id, createdAt, updatedAt, username, password, accountType);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating account: " + e.getMessage());
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
        } finally {
             try {
                 if (resultSet != null) resultSet.close();
                 if (statement != null) statement.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }

        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM accounts";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                AccountType type = AccountType.valueOf(resultSet.getString("type"));
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Account account = new Account(id, createdAt, updatedAt, username, password, type);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching account: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

    public void updateAccount(UpdateAccountRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE accounts SET username = ?, password = ? WHERE id = ?";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, request.getUsername());
            statement.setString(2, request.getPassword());
            statement.setInt(3, request.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating account: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}


