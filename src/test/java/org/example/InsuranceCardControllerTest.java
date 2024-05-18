package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Request.InsertInsuranceCardRequest;

import java.sql.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceCardControllerTest {

    private InsuranceCardController insuranceCardController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceCardController = new InsuranceCardController();
    }

    @Test
    public void testGetInsuranceCardByCustomerId() {
        int customerId = 1;
        InsuranceCard insuranceCard = insuranceCardController.getInsuranceCardByCustomerID(customerId);

        InsuranceCard expectedInsuranceCard = new InsuranceCard(4,
                Date.valueOf("2024-05-15"),
                Date.valueOf("2024-05-15"),
                customerId,
                "1234567890123456",
                Date.valueOf("2024-12-31"),
                "VPBank",
                "12390");

        assertEquals(insuranceCard.getId(), expectedInsuranceCard.getId());
        assertEquals(insuranceCard.getCustomerId(), expectedInsuranceCard.getCustomerId());
        assertEquals(insuranceCard.getCardNumber(), expectedInsuranceCard.getCardNumber());
        assertNotEquals(insuranceCard.getAccountNumber(), expectedInsuranceCard.getAccountNumber());

    }

    @Test
    public void testCreateInsuranceCard() {
        InsertInsuranceCardRequest request = new InsertInsuranceCardRequest(1,
                                                                "1234567890123456",
                                                                            Date.valueOf("2024-12-31"), "VPBank", "1234567890");
        insuranceCardController.createInsuranceCard(request);
    }

    @Test
    public void testDeleteInsuranceCardById() {
        int customerId = 1;

        InsuranceCard insuranceCard = insuranceCardController.getInsuranceCardByCustomerID(customerId);

        assertNotNull(insuranceCard, "The insurance card should exist.");

        insuranceCardController.deleteInsuranceCardById(insuranceCard.getId());

        InsuranceCard deletedInsuranceCard = insuranceCardController.getInsuranceCardByCustomerID(customerId);

        assertNull(deletedInsuranceCard, "The insurance card should NOT exist.");

    }

    @Test
    public void testGetInsuranceCardById() {
        int id = 5;

        InsuranceCard insuranceCard = insuranceCardController.getInsuranceCardById(id);

        assertNotNull(insuranceCard, "The insurance card should not be null");
        assertEquals(id, insuranceCard.getId(), "The card ID should match");
    }
}

