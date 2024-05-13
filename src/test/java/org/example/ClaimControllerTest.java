package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimStatus;
import com.team2.a2.Model.User.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
                8, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 23435.00, ClaimStatus.PROCESSING);

        Claim notExpectedClaim = new Claim(1, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                8, Date.valueOf("2024-05-10"), Date.valueOf("2024-04-29"), 0.00, ClaimStatus.NEW);

        Claim actualClaim = claimController.getClaimById(claimID);

        // True Case
        assertEquals(expectedClaim.getId(), actualClaim.getId());
        assertEquals(expectedClaim.getAmount(), actualClaim.getAmount());
        assertEquals(expectedClaim.getStatus(), actualClaim.getStatus());

        // False Case
        assertNotEquals(notExpectedClaim.getId(), actualClaim.getId());
        assertNotEquals(notExpectedClaim.getAmount(), actualClaim.getAmount());
        assertNotEquals(notExpectedClaim.getStatus(), actualClaim.getStatus());
    }

}
