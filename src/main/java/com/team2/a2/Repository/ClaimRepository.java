package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.Status;
import com.team2.a2.Model.User.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClaimRepository {

    private Connection connection;

    public ClaimRepository() {
        this.connection = ConnectionManager.getConnection();
    }


    public Claim getClaimById(int id) {
            Claim claim = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                String sql = "SELECT * FROM claims WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                   Date createdAt = resultSet.getDate("created_at");
                    Date updatedAt = resultSet.getDate("updated_at");
                    int customerId = resultSet.getInt("customer_id");

                    Date claimDate = resultSet.getDate("claim_date");
                    Date examDate = resultSet.getDate("exam_date");
                    Double amount = resultSet.getDouble("amount");
//                    Status status = resultSet.getString("status");

//                    claim = new Account(id, createdAt, updatedAt, customerId, claimDate, type);
                    return claim;
                }

            } catch (SQLException e) {
                System.err.println("Error fetching account: " + e.getMessage());
            }

            return claim;
        }
}
