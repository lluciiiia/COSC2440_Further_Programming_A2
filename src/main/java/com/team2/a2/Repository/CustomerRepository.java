package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.sql.*;
import java.util.*;
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
                int policyOwnerId = resultSet.getInt("policy_owner_id");
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
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    public List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId) {
        List<Customer> customers = new ArrayList<Customer>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM customers WHERE policy_owner_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, policyOwnerId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String typeString = resultSet.getString("type");
                CustomerType type = CustomerType.valueOf(typeString);

                Customer customer = new Customer(id, createdAt, updatedAt, accountId, policyOwnerId, name, address, phoneNumber, email, type);

                customers.add(customer);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching customers: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM customers WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyOwnerId = resultSet.getInt("policy_owner_id");
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
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    public Customer createCustomer(InsertCustomerRequest request, int accountId, int policyOwnerId) {
        Customer customer = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "INSERT INTO customers (account_id, policy_owner_id, name, address, phone_number, email, type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, accountId);
            statement.setInt(2, policyOwnerId);
            statement.setString(3, request.getName());
            statement.setString(4, request.getAddress());
            statement.setString(5, request.getPhoneNumber());
            statement.setString(6, request.getEmail());
            statement.setObject(7, request.getType(), Types.OTHER);

            // Execute the insert operation
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                // If insertion successful, retrieve the newly created customer's ID
                resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1); // Retrieve the auto-generated ID

                    Date createdAt = resultSet.getDate("created_at");
                    Date updatedAt = resultSet.getDate("updated_at");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String typeString = resultSet.getString("type");
                    CustomerType type = CustomerType.valueOf(typeString);

                    // Create the customer object
                    customer = new Customer(id, createdAt, updatedAt, accountId, policyOwnerId,
                            name, address, phoneNumber, email, type);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating customer: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    public Customer updateCustomer(UpdateCustomerRequest request) {
        Customer customer = null;
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE customers SET name = ?, address = ?, phone_number = ?, email = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, request.getName());
            statement.setString(2, request.getAddress());
            statement.setString(3, request.getPhoneNumber());
            statement.setString(4, request.getEmail());
            statement.setInt(5, request.getId());

            // Execute the update operation
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // If update successful, fetch the updated customer
                customer = getCustomerById(request.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM customers";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int accountId = resultSet.getInt("account_id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int policyOwnerId = resultSet.getInt("policy_owner_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String typeString = resultSet.getString("type");
                CustomerType type = CustomerType.valueOf(typeString);

                Customer customer = new Customer(id, createdAt, updatedAt, accountId, policyOwnerId, name, address, phoneNumber, email, type);

                customers.add(customer);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching customers: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }

}
