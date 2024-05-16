package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClaimControllerTest {

    private ClaimController claimController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        claimController = new ClaimController();
    }

    @Test
    public void testGetClaimById() {
        int claimID = 6;
        Claim expectedClaim = new Claim(claimID, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                8, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 23435.00, ClaimStatus.PROCESSING, false);

        Claim notExpectedClaim = new Claim(1, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                8, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 0.00, ClaimStatus.NEW, true);

        Claim actualClaim = claimController.getClaimById(claimID);

        // True Case
        assertEquals(expectedClaim.getId(), actualClaim.getId());
        assertEquals(expectedClaim.getAmount(), actualClaim.getAmount());
        assertEquals(expectedClaim.getStatus(), actualClaim.getStatus());
        assertEquals(expectedClaim.getDocumentRequested(), actualClaim.getDocumentRequested());

        // False Case
        assertNotEquals(notExpectedClaim.getId(), actualClaim.getId());
        assertNotEquals(notExpectedClaim.getAmount(), actualClaim.getAmount());
        assertNotEquals(notExpectedClaim.getStatus(), actualClaim.getStatus());
        assertNotEquals(notExpectedClaim.getDocumentRequested(), actualClaim.getDocumentRequested());
    }

    @Test
    public void testGetAllClaims() {
        List<Claim> claims = claimController.getAllClaims();

        assertNotNull(claims, "Claims should NOT be null.");
        assertEquals(4, claims.size(), "The size of the returned list should be 4.");
    }

    @Test
    public void testGetClaimsByCustomerId() {
        int customerID = 8;
        Claim expectedClaim = new Claim(6, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                customerID, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 23435.00, ClaimStatus.PROCESSING, false);

        Claim notExpectedClaim = new Claim(1, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                6, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 0.00, ClaimStatus.NEW, false);

        List<Claim> actualClaims = claimController.getClaimsByCustomerId(customerID);

        // True Case
        assertEquals(actualClaims.size(), 1);
        assertEquals(actualClaims.get(0).getAmount(), expectedClaim.getAmount());
        assertEquals(actualClaims.get(0).getStatus(), expectedClaim.getStatus());

        // False Case
        assertNotEquals(actualClaims.size(), 2);
        assertNotEquals(actualClaims.get(0).getAmount(), notExpectedClaim.getAmount());
        assertNotEquals(actualClaims.get(0).getStatus(), notExpectedClaim.getStatus());
    }

    @Test
    public void testDeleteClaimById() {
        int id = 7;

        claimController.deleteClaimById(id);

        Claim claim = claimController.getClaimById(id);

        assertNull(claim, "Claim should be null.");
    }

    @Test
    void testUpdateClaimStatus() {
        int claimId = 7;
        ClaimStatus newClaimStatus = ClaimStatus.PROCESSING;

        Claim previousClaim = claimController.getClaimById(claimId);
        claimController.updateClaimStatus(claimId, newClaimStatus);

        Claim updatedClaim = claimController.getClaimById(claimId);

        assertNotEquals(previousClaim.getStatus(), updatedClaim.getStatus());
    }

    @Test
    public void testCreateClaim() {
        InsertClaimRequest request = new InsertClaimRequest(8, Date.valueOf("2027-02-12"), Date.valueOf("2027-02-12"), 2345.00);

        claimController.createClaim(request);
    }

    @Test
    public void testUpdateClaimDocumentRequested() {
        int claimId = 7;

        Claim previousClaim = claimController.getClaimById(claimId);
        boolean newDocumentRequested = !previousClaim.getDocumentRequested();

        claimController.updateClaimDocumentRequested(claimId, newDocumentRequested);

        Claim updatedClaim = claimController.getClaimById(claimId);

        assertNotEquals(previousClaim.getDocumentRequested(), updatedClaim.getDocumentRequested());
    }

    @Test
    public void testUpdateClaim() {
        int claimId = 8;

        UpdateClaimRequest updateRequest = new UpdateClaimRequest(claimId,
                Date.valueOf("2024-05-01"), Date.valueOf("2024-05-15"), 2500.0);
        claimController.updateClaim(updateRequest);

        Claim updatedClaim = claimController.getClaimById(claimId);

        assertEquals(updateRequest.getClaimDate(), updatedClaim.getClaimDate(), "Claim date should be updated correctly.");
        assertEquals(updateRequest.getExamDate(), updatedClaim.getExamDate(), "Exam date should be updated correctly.");
        assertEquals(updateRequest.getAmount(), updatedClaim.getAmount(), 0.01, "Amount should be updated correctly.");
    }

}
