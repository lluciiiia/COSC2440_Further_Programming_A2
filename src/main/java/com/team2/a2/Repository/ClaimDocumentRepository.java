package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDocumentRepository {

    private Connection connection;

    public ClaimDocumentRepository() {
        this.connection = ConnectionManager.getConnection();
    }


    public void createClaimDocument(InsertClaimDocumentRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO claim_documents (claim_id, image_src) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, request.getClaimId());
            statement.setString(2, request.getImageSrc());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating claim document: " + e.getMessage());
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

    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) {
        List<ClaimDocument> claimDocuments = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM claim_documents WHERE claim_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, claimId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");
                String imageSrc = resultSet.getString("image_src");

                ClaimDocument claimDocument = new ClaimDocument(id, createdAt, updatedAt, claimId, imageSrc);
                claimDocuments.add(claimDocument);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching claim document: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return claimDocuments;
    }

    public void updateClaimDocument(UpdateClaimDocumentRequest request) {
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE claim_documents SET image_src = ?, updated_at = NOW() WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, request.getImageSrc());
            statement.setInt(2, request.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Claim document updated successfully!");
            } else {
                System.out.println("No claim document found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating claim document: " + e.getMessage());
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

    public ClaimDocument getClaimDocumentById(int id) {
        ClaimDocument claimDocument = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM claim_documents WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int claimId = resultSet.getInt("claim_id");
                String imageSrc = resultSet.getString("image_src");
                Date createdAt = resultSet.getDate("created_at");
                Date updatedAt = resultSet.getDate("updated_at");

                claimDocument = new ClaimDocument(id, createdAt, updatedAt, claimId, imageSrc);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching claim document: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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

        return claimDocument;
    }

    public void deleteClaimDocumentById(int id) {
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM claim_documents WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting claim document: " + e.getMessage());
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

}
