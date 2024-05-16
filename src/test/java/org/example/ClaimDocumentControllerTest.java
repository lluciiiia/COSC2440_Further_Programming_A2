package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.ClaimDocumentController;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ClaimDocumentControllerTest {

    private ClaimDocumentController claimDocumentController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        claimDocumentController = new ClaimDocumentController();
    }

    @Test
    public void testCreateClaimDocument() {
        InsertClaimDocumentRequest request = new InsertClaimDocumentRequest(7, "sample_image.jpg");

        claimDocumentController.createClaimDocument(request);

        List<ClaimDocument> claimDocuments = claimDocumentController.getClaimDocumentsByClaimId(request.getClaimId());

        assertNotEquals(0, claimDocuments, "Claim documents should exist after creation");
    }

    @Test
    public void testGetClaimDocumentsByClaimId() {
        int claimId = 7;

        List<ClaimDocument> claimDocuments = claimDocumentController.getClaimDocumentsByClaimId(claimId);

        assertNotEquals(0, claimDocuments, "Claim document should not be null");
    }
}
