package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.UpdateInsuranceManagerRequest;

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

            while (resultSet.next()) {
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

    public void deleteInsuranceManagerById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM insurance_managers WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An insurance manager was deleted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting manager surveyor: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createInsuranceManager(InsertInsuranceManagerRequest request, int accountId) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO insurance_managers (account_id, company_name, company_address, phone_number, email, name) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, accountId);
            statement.setString(2, request.getCompanyName());
            statement.setString(3, request.getCompanyAddress());
            statement.setString(4, request.getPhoneNumber());
            statement.setString(5, request.getEmail());
            statement.setString(6, request.getName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new insurance manager was inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error creating insurance manager: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public InsuranceManager getInsuranceManagerById(int insuranceManagerId) {
        InsuranceManager insuranceManager = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_managers WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, insuranceManagerId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                insuranceManager = new InsuranceManager(insuranceManagerId, createdAt, updatedAt, accountId, companyName, companyAddress, phoneNumber, email, name);
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

    public void updateInsuranceManager(UpdateInsuranceManagerRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE insurance_managers SET company_name = ?, company_address = ?, phone_number = ?, email = ?, name = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, request.getCompanyName());
            statement.setString(2, request.getCompanyAddress());
            statement.setString(3, request.getPhoneNumber());
            statement.setString(4, request.getEmail());
            statement.setString(5, request.getName());
            statement.setInt(6, request.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating insurance manager: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
