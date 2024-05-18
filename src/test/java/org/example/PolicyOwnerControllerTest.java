package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
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
    public void testGetPolicyOwnerByAccountId() {
        int accountId = 4;

        PolicyOwner expectedPolicyOwner = new PolicyOwner(1, Date.valueOf("2024-05-05"),
                Date.valueOf("2024-05-05"), accountId, "impppooo");

        PolicyOwner actualPolicyOwner = policyOwnerController.getPolicyOwnerByAccountId(accountId);

        assertNotNull(actualPolicyOwner, "Policy owner should exist.");
        assertEquals(expectedPolicyOwner.getId(), actualPolicyOwner.getId(), "The id should be the same.");
        assertEquals(expectedPolicyOwner.getName(), actualPolicyOwner.getName(), "The name should be the same.");
    }

    @Test
    public void testGetAllPolicyOwners() {
        List<PolicyOwner> policyOwners = policyOwnerController.getAllPolicyOwners();

        assertNotNull(policyOwners, "The returned list should not be null");

        assertEquals(1, policyOwners.size(), "The size of the returned list should be 1");
        assertEquals("impppooo", policyOwners.get(0).getName(), "The name of the first policy owner should be impppooo");
    }

    @Test
    public void testCreatePolicyOwner() throws Exception {
        InsertPolicyOwnerRequest request = new InsertPolicyOwnerRequest("PO username", "12345", "PO name");

        policyOwnerController.createPolicyOwner(request);
    }

    @Test
    public void testDeletePolicyOwnerById() throws Exception {
        int id = 1;

        policyOwnerController.deletePolicyOwnerById(id);

        PolicyOwner deletedPolicyOwner = policyOwnerController.getPolicyOwnerById(id);
        assertNull(deletedPolicyOwner, "Policy owner should be deleted successfully");
    }


    @Test
    public void testGetPolicyOwnerById() {
        int id = 1;

        PolicyOwner policyOwner = policyOwnerController.getPolicyOwnerById(id);

        assertNotNull(policyOwner, "The policy owner should not be null");
        assertEquals(id, policyOwner.getId(), "The policy owner ID should match");
    }

    @Test
        public void testUpdatePolicyOwner() throws Exception {
        int id = 3;

        UpdatePolicyOwnerRequest request = new UpdatePolicyOwnerRequest(id, "Jane Smith");

        policyOwnerController.updatePolicyOwner(request);
        PolicyOwner updatedPolicyOwner = policyOwnerController.getPolicyOwnerById(id);

        assertEquals(request.getName(), updatedPolicyOwner.getName(), "The name should be the same.");
    }


}
