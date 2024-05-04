package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;
    public UserRepository() {
        this.connection = ConnectionManager.getConnection();
    }
    public User getUser(String userName, String password) {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // If user found, reate a User object
//               user = new User();
//               user.setId(resultSet.getInt("id"));
//               user.setUsername(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }

        return user;
    }
}
