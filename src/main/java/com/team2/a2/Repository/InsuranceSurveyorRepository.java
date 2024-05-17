package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceSurveyorRepository {

    private Connection connection;
    public InsuranceSurveyorRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public InsuranceSurveyor getInsuranceSurveyorByAccountId(int accountId) {
        InsuranceSurveyor insuranceSurveyor = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_surveyors WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int insuranceManagerId = resultSet.getInt("insurance_manager_id");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                insuranceSurveyor = new InsuranceSurveyor(id, createdAt, updatedAt, accountId, insuranceManagerId, companyName, companyAddress, phoneNumber, email, name);

                return insuranceSurveyor;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance surveyors: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return insuranceSurveyor;
    }

    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerID(int insuranceManagerId) {
        List<InsuranceSurveyor> insuranceSurveyors = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_surveyors WHERE insurance_manager_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, insuranceManagerId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int accountId = resultSet.getInt("account_id");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor(id, createdAt, updatedAt, accountId, insuranceManagerId, companyName, companyAddress, phoneNumber, email, name);
                insuranceSurveyors.add(insuranceSurveyor);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance surveyors: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }

        return insuranceSurveyors;
    }

    public List<InsuranceSurveyor> getAllInsuranceSurveyors() {
        List<InsuranceSurveyor> insuranceSurveyors = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_surveyors";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int accountId = resultSet.getInt("account_id");
                int insuranceManagerId = resultSet.getInt("insurance_manager_id");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");

                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor(id, createdAt, updatedAt, accountId, insuranceManagerId, companyName, companyAddress, phoneNumber, email, name);
                insuranceSurveyors.add(insuranceSurveyor);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance surveyors: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }

        return insuranceSurveyors;
    }

    public void deleteInsuranceSurveyorById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM insurance_surveyors WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An insurance surveyor was deleted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting insurance surveyor: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request, int accountId) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO insurance_surveyors (account_id, insurance_manager_id, company_name, company_address, phone_number, email, name) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, accountId);
            statement.setInt(2, request.getInsuranceManagerId());
            statement.setString(3, request.getCompanyName());
            statement.setString(4, request.getCompanyAddress());
            statement.setString(5, request.getPhoneNumber());
            statement.setString(6, request.getEmail());
            statement.setString(7, request.getName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new insurance surveyor was inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error creating insurance surveyor: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
