package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerRepository {

    private Connection connection;
    public CustomerRepository() {
        this.connection = ConnectionManager.getConnection();
    }

    public Customer getCustomerByAccountId(int accountId) {
        Customer customer = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM customers WHERE account_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyOwnerId = resultSet.getInt("policyowner_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String typeString = resultSet.getString("type");
                CustomerType type = CustomerType.valueOf(typeString);

                customer = new Customer(id, createdAt, updatedAt, accountId, policyOwnerId, name, address, phoneNumber, email, type);

                return customer;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching customer: " + e.getMessage());
        }

        return customer;
    }

}
