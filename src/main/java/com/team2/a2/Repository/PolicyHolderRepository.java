package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.PolicyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PolicyHolderRepository {

    private Connection connection;
    public PolicyHolderRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public PolicyHolder getPolicyHolderByAccountId(int accountId) {
        PolicyHolder policyHolder = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM policy_holders WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyOwnerId = resultSet.getInt("policyowner_id");
                String name = resultSet.getString("name");
                String homeAddress = resultSet.getString("home_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");

                policyHolder = new PolicyHolder(id, createdAt, updatedAt, accountId, policyOwnerId, name, homeAddress, phoneNumber, email); // TODO: create a policy holder object based on the result.

                return policyHolder;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching policyholder: " + e.getMessage());
        }

        return policyHolder;
    }

}
