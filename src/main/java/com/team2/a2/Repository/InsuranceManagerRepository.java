package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Provider.InsuranceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return insuranceManager;
    }

    public List<InsuranceManager> getAllInsuranceManagers() {
        List<InsuranceManager> insuranceManagers = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_managers";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                InsuranceManager insuranceManager = new InsuranceManager(id, createdAt, updatedAt, accountId, companyName, companyAddress, phoneNumber, email, name);

                insuranceManagers.add(insuranceManager);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance manager: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return insuranceManagers;
    }
}
