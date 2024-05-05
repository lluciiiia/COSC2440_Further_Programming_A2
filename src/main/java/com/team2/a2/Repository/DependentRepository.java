package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Dependent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DependentRepository {

    private Connection connection;
    public DependentRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public Dependent getDependentByAccountId(int accountId) {
        Dependent dependent = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM dependents WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyOwnerId = resultSet.getInt("policyowner_id");
                int policyHolderId = resultSet.getInt("policyholder_id");
                String name = resultSet.getString("name");
                String homeAddress = resultSet.getString("home_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");

                dependent = new Dependent(id, createdAt, updatedAt, accountId, policyOwnerId, policyHolderId, name, homeAddress, phoneNumber, email);

                return dependent;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching dependent: " + e.getMessage());
        }
        return dependent;
    }
}
