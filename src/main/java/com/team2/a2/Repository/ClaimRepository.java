package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                    String statusString = resultSet.getString("status");

                    ClaimStatus status = ClaimStatus.valueOf(statusString);

                    claim = new Claim(id, createdAt, updatedAt, customerId, claimDate, examDate, amount, status);
                    return claim;
                }

            } catch (SQLException e) {
                System.err.println("Error fetching claim: " + e.getMessage());
            }

            return claim;
        }

    public List<Claim> getClaimsByCustomerId(int customerId) {
        List<Claim> claims = new ArrayList<Claim>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM claims WHERE customer_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                Date claimDate = resultSet.getDate("claim_date");
                Date examDate = resultSet.getDate("exam_date");
                Double amount = resultSet.getDouble("amount");
                String statusString = resultSet.getString("status");

                ClaimStatus status = ClaimStatus.valueOf(statusString);

                Claim claim = new Claim(id, createdAt, updatedAt, customerId, claimDate, examDate, amount, status);

                claims.add(claim);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching claims: " + e.getMessage());
        }

        return claims;
    }
}
