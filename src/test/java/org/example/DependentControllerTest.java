package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.DependentController;
import com.team2.a2.Model.User.Customer.Dependent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DependentControllerTest {

    private DependentController dependentController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        dependentController = new DependentController();
    }

    @Test
    public void testGetDependentByCustomerId() {
        int customerId = 17;

        Dependent expectedDependent = new Dependent(7, Date.valueOf("2024-05-16"), Date.valueOf("2024-05-16"), customerId, 12);

        Dependent actualDependent = dependentController.getDependentByCustomerId(customerId);

        assertEquals(expectedDependent.getId(), actualDependent.getId());
        assertEquals(expectedDependent.getCustomerId(), actualDependent.getCustomerId());
        assertEquals(expectedDependent.getPolicyHolderId(), actualDependent.getPolicyHolderId());
    }
}