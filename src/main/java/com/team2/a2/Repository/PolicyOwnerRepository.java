package com.team2.a2.Repository;

/**
 * @author <Team 2>
 */

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import java.sql.*;
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
            System.err.println("Error fetching policy owner: " + e.getMessage());
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

    public void createPolicyOwner(InsertPolicyOwnerRequest request, int accountId) {
        PreparedStatement statement = null;

        try {

            String sql = "INSERT INTO policy_owners (account_id, name) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, accountId);
            statement.setString(2, request.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating policy owner: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PolicyOwner getPolicyOwnerById(int id) {
        PolicyOwner policyOwner = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM policy_owners WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String name = resultSet.getString("name");

                policyOwner = new PolicyOwner(id, createdAt, updatedAt, accountId, name);

                return policyOwner;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching policy owner: " + e.getMessage());
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

    public void deletePolicyOwnerById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM policy_owners WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A policy owner was deleted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting policy owner: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePolicyOwner(UpdatePolicyOwnerRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE policy_owners SET name = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, request.getName());
            statement.setInt(2, request.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating policy owner: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
