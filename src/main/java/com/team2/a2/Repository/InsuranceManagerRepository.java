package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Provider.InsuranceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InsuranceManagerRepository {
    private Connection connection;
    public InsuranceManagerRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public InsuranceManager getInsuranceManagerByAccountId(int accountId) {
        InsuranceManager insuranceManager = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_managers WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                insuranceManager = new InsuranceManager(id, createdAt, updatedAt, accountId, companyName, companyAddress, phoneNumber, email, name);
                return insuranceManager;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance manager: " + e.getMessage());
        }

        return insuranceManager;
    }
}
