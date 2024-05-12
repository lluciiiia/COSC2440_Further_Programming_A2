package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        }

        return dependents;
    }
}
