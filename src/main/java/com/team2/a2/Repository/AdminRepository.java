package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminRepository {

    private Connection connection;
    public AdminRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public Admin getAdminByAccountId(int accountId) {
        Admin admin = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM admins WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String name = resultSet.getString("name");

                admin = new Admin(id, createdAt, updatedAt, accountId, name);
                return admin;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching admin: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    public void deleteAdminById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM admins WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An admin was deleted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting admin: " + e.getMessage());
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
