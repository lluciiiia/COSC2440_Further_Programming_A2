package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Request.InsertClaimDocumentRequest;

import java.sql.*;

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

}
