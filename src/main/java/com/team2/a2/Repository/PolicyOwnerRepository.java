package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            String sql = "SELECT * FROM policy_owners WHERE account_id = ?";
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
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return policyOwner;
    }

    public List<PolicyOwner> getAllPolicyOwners() {
        List<PolicyOwner> policyOwners = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM policy_owners";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String name = resultSet.getString("name");
                int accountId = resultSet.getInt("account_id");

                PolicyOwner policyOwner = new PolicyOwner(id, createdAt, updatedAt, accountId, name);

                policyOwners.add(policyOwner);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching dependent: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return policyOwners;
    }
}
