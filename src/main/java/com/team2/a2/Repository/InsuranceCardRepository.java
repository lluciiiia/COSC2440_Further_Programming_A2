package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Admin;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.InsertInsuranceCardRequest;

import java.sql.*;

public class InsuranceCardRepository {

    private Connection connection;
    public InsuranceCardRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public void createInsuranceCard(InsertInsuranceCardRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO insurance_cards (customer_id, card_number, expiry_date, bank_name, account_number) " +
                    "VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, request.getCustomerId());
            statement.setString(2, request.getCardNumber());
            statement.setDate(3, request.getExpiryDate());
            statement.setString(4, request.getBankName());
            statement.setString(5, request.getAccountNumber());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating insurance card: " + e.getMessage());
        }

    }

    public InsuranceCard getInsuranceCard(String cardNumber, Date expiryDate) {
        InsuranceCard insuranceCard = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_cards WHERE card_number = ? AND expiry_date = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cardNumber);
            statement.setDate(2, expiryDate);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                java.util.Date createdAt = resultSet.getDate("created_at");
                java.util.Date updatedAt = resultSet.getDate("updated_at");
                int customerId = resultSet.getInt("customer_id");
                String bankName = resultSet.getString("bank_name");
                String accountNumber = resultSet.getString("account_number");

                insuranceCard = new InsuranceCard(id, createdAt, updatedAt, customerId, cardNumber, expiryDate, bankName, accountNumber);
                return insuranceCard;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance card: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insuranceCard;
    }

    public InsuranceCard getInsuranceCardByCustomerID(int customerId) {
        InsuranceCard insuranceCard = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM insurance_cards WHERE customer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                java.util.Date createdAt = resultSet.getDate("created_at");
                java.util.Date updatedAt = resultSet.getDate("updated_at");
                String cardNumber = resultSet.getString("card_number");
                Date expiryDate = resultSet.getDate("expiry_date");
                String bankName = resultSet.getString("bank_name");
                String accountNumber = resultSet.getString("account_number");

                insuranceCard = new InsuranceCard(id, createdAt, updatedAt, customerId, cardNumber, expiryDate, bankName, accountNumber);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching insurance card by customer ID: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insuranceCard;
    }
}
