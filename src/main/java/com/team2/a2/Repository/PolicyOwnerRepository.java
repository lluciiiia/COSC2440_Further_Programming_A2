package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.PolicyOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PolicyOwnerRepository {

    private Connection connection;
    public PolicyOwnerRepository() {
        this.connection = ConnectionManager.getConnection();
    }


    public PolicyOwner getPolicyOwnerByAccountId(int accountId) {
        PolicyOwner policyOwner = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM policyowners WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String name = resultSet.getString("name");

                policyOwner = new PolicyOwner(id, createdAt, updatedAt, accountId, name);

                return policyOwner;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching dependent: " + e.getMessage());
        }
        return policyOwner;
    }
}
