package com.team2.a2.Repository;

/**
 * @author <Team 2>
 */

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;

import java.sql.*;
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
                    boolean documentRequested = resultSet.getBoolean("document_requested");

                    ClaimStatus status = ClaimStatus.valueOf(statusString);

                    claim = new Claim(id, createdAt, updatedAt, customerId, claimDate, examDate, amount, status, documentRequested);
                    return claim;
                }

            } catch (SQLException e) {
                System.err.println("Error fetching claim: " + e.getMessage());
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
                boolean documentRequested = resultSet.getBoolean("document_requested");

                ClaimStatus status = ClaimStatus.valueOf(statusString);

                Claim claim = new Claim(id, createdAt, updatedAt, customerId, claimDate, examDate, amount, status, documentRequested);

                claims.add(claim);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching claims: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return claims;
    }

    public void deleteClaimById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM claims WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error deleting claim: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateClaimStatus(int id, ClaimStatus status) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE claims SET status = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, status, Types.OTHER);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating claim status: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM claims";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                int customerId = resultSet.getInt("customer_id");
                Date claimDate = resultSet.getDate("claim_date");
                Date examDate = resultSet.getDate("exam_date");
                Double amount = resultSet.getDouble("amount");
                String statusString = resultSet.getString("status");
                ClaimStatus status = ClaimStatus.valueOf(statusString.toUpperCase());
                boolean documentRequested = resultSet.getBoolean("document_requested");

                Claim claim = new Claim(id, createdAt, updatedAt, customerId, claimDate, examDate, amount, status, documentRequested);
                claims.add(claim);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching claims: " + e.getMessage());
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

        return claims;
    }

    public void updateClaimDocumentRequested(int id, boolean isRequested) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE claims SET document_requested = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, isRequested);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error updating claim documentRequested: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public void createClaim(InsertClaimRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO claims (customer_id, claim_date, exam_date, amount, status) " +
                    "VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, request.getCustomerId());
            statement.setDate(2, request.getClaimDate());
            statement.setDate(3, request.getExamDate());
            statement.setDouble(4, request.getAmount());
            statement.setObject(5, ClaimStatus.NEW, Types.OTHER);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new claim was inserted successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error creating claim: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public void updateClaim(UpdateClaimRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE claims SET claim_date = ?, exam_date = ?, amount = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, request.getClaimDate());
            statement.setDate(2, request.getExamDate());
            statement.setDouble(3, request.getAmount());
            statement.setInt(4, request.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Claim was updated successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error updating claim: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    public Double getAcceptedClaimsTotalAmount() {
        Double totalAmount = 0.0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT SUM(amount) AS total_amount FROM claims WHERE status = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, ClaimStatus.ACCEPTED, Types.OTHER);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalAmount = resultSet.getDouble("total_amount");
            }

        } catch (SQLException e) {
            System.err.println("Error fetching accepted claims total amount: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalAmount;
    }

}
