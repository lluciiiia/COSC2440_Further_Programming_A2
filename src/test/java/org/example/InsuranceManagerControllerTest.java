package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Controller.PolicyOwnerController;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceManagerControllerTest {

    private InsuranceManagerController insuranceManagerController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceManagerController = new InsuranceManagerController();
    }

    @Test
    public void testGetAllInsuranceManagers() {
        List<InsuranceManager> insuranceManagers = insuranceManagerController.getAllInsuranceManagers();

        assertNotNull(insuranceManagers, "The returned list should not be null");

        assertEquals(1, insuranceManagers.size(), "The size of the returned list should be 1");
        assertEquals("im_ismmm", insuranceManagers.get(0).getName(), "The name of the first insurance manager should be im_ismmm");
        assertEquals("rmit", insuranceManagers.get(0).getCompanyName(), "The name of the first insurance manager should be rmit");

    }


}
