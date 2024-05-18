package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DependentRepository {

    private Connection connection;
    public DependentRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public Dependent getDependentByCustomerId(int customerId) {
        Dependent dependent = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM dependents WHERE customer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyHolderId = resultSet.getInt("policy_holder_id");

                dependent = new Dependent(id, createdAt, updatedAt, customerId, policyHolderId);

                return dependent;
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
        return dependent;
    }

    public List<Dependent> getDependentsByPolicyHolderId(int policyHolderId) {
        List<Dependent> dependents = new ArrayList<Dependent>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM dependents WHERE policy_holder_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, policyHolderId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int customerId = resultSet.getInt("customer_id");

                Dependent dependent = new Dependent(id, createdAt, updatedAt, customerId, policyHolderId);

                dependents.add(dependent);
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

        return dependents;
    }

    public void createDependent(int customerId, int policyHolderId) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO dependents (customer_id, policy_holder_id) " +
                    "VALUES (?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, customerId);
            statement.setInt(2, policyHolderId);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating dependent: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteDependentById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM dependents WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting dependent: " + e.getMessage());
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

    public Dependent getDependentById(int id) {
        Dependent dependent = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM dependents WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int customerId = resultSet.getInt("customer_id");
                int policyHolderId = resultSet.getInt("policy_holder_id");

                dependent = new Dependent(id, createdAt, updatedAt, customerId, policyHolderId);
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
        return dependent;
    }

}
