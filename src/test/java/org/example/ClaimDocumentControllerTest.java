package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.ClaimDocumentController;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;
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

    @Test
    public void testUpdateClaimDocument() {
        int id = 1;

        UpdateClaimDocumentRequest updateRequest = new UpdateClaimDocumentRequest(id, "new_image_src.jpg");

        claimDocumentController.updateClaimDocument(updateRequest);

        ClaimDocument updatedClaimDocument = claimDocumentController.getClaimDocumentById(id);

        assertEquals(updateRequest.getImageSrc(), updatedClaimDocument.getImageSrc(), "The image source should be updated");
    }

    @Test
    public void testGetClaimDocumentById() {
        int id = 1;

        ClaimDocument claimDocument = claimDocumentController.getClaimDocumentById(id);

        assertNotNull(claimDocument, "ClaimDocument should not be null");
        assertEquals(id, claimDocument.getId(), "The ID should match");
    }

    @Test
    public void testDeleteClaimDocumentById() {
        int id = 1;

        InsertClaimDocumentRequest insertRequest = new InsertClaimDocumentRequest(id, "image_src.jpg");
        claimDocumentController.createClaimDocument(insertRequest);

        ClaimDocument claimDocument = claimDocumentController.getClaimDocumentById(id);
        assertNotNull(claimDocument, "ClaimDocument should exist before deletion");

        claimDocumentController.deleteClaimDocumentById(id);

        ClaimDocument deletedClaimDocument = claimDocumentController.getClaimDocumentById(id);
        assertNull(deletedClaimDocument, "ClaimDocument should be null after deletion");
    }


}
