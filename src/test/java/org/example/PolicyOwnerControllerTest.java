package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PolicyOwnerControllerTest {

    private PolicyOwnerController policyOwnerController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        policyOwnerController = new PolicyOwnerController();
    }

    @Test
    public void testGetAllPolicyOwners() {
        List<PolicyOwner> policyOwners = policyOwnerController.getAllPolicyOwners();

        assertNotNull(policyOwners, "The returned list should not be null");

        assertEquals(1, policyOwners.size(), "The size of the returned list should be 1");
        assertEquals("impppooo", policyOwners.get(0).getName(), "The name of the first policy owner should be impppooo");
    }

    @Test
    public void testCreatePolicyOwner() {
        InsertPolicyOwnerRequest request = new InsertPolicyOwnerRequest("PO username", "12345", "PO name");

        policyOwnerController.createPolicyOwner(request);
    }


}
