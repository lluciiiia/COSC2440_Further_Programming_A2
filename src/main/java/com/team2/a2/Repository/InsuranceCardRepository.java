package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.InsertInsuranceCardRequest;

import java.sql.*;
import java.util.Date;

public class InsuranceCardRepository {

    private Connection connection;
    public InsuranceCardRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public void createInsuranceCard(InsertInsuranceCardRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO insurance_cards (customer_id, card_number, expiry_date) " +
                    "VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, request.getCustomerId());
            statement.setString(2, request.getCardNumber());
            statement.setDate(3, request.getExpiryDate());

        } catch (SQLException e) {
            System.err.println("Error creating insurance card: " + e.getMessage());
        }

    }

}