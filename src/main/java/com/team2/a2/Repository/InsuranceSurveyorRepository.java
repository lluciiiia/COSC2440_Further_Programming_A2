package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
        }

        return insuranceSurveyor;
    }
}
