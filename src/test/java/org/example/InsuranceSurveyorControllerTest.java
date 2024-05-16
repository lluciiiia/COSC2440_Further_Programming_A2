package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceSurveyorControllerTest {

    private InsuranceSurveyorController insuranceSurveyorController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceSurveyorController = new InsuranceSurveyorController();
    }

    @Test
    public void testGetInsuranceSurveyorByAccountId() {
        int accountId = 6;

        InsuranceSurveyor expectedInsuranceSurveyor = new InsuranceSurveyor(1, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
                accountId, 2, "rmit-surveyor", "123 pham van dong", "1233", "sd@gmail.com", "im_isssss");

        InsuranceSurveyor actualInsuranceSurveyor = insuranceSurveyorController.getInsuranceSurveyorByAccountId(accountId);

        assertNotNull(expectedInsuranceSurveyor, "The insurance surveyor should exist.");
        assertEquals(expectedInsuranceSurveyor.getId(), actualInsuranceSurveyor.getId(), "The id should be the same.");
        assertEquals(expectedInsuranceSurveyor.getInsuranceManagerId(), actualInsuranceSurveyor.getInsuranceManagerId(), "The insurance manager id should be the same.");
        assertEquals(expectedInsuranceSurveyor.getName(), actualInsuranceSurveyor.getName(), "The name should be the same.");
        assertEquals(expectedInsuranceSurveyor.getCompanyName(), actualInsuranceSurveyor.getCompanyName(), "The company name should be the same.");
        assertEquals(expectedInsuranceSurveyor.getCompanyAddress(), actualInsuranceSurveyor.getCompanyAddress(), "The address should be the same.");
    }

    @Test
    public void testGetInsuranceSurveyorsByManagerId() {
        int managerId = 2;

        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorController.getInsuranceSurveyorsByManagerId(managerId);

        assertNotNull(insuranceSurveyors, "Insurance surveyors should NOT be null.");
        assertEquals(1, insuranceSurveyors.size(), "Insurance surveyors size should be 1.");
    }

    @Test
    public void testGetAllInsuranceSurveyors() {
        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorController.getAllInsuranceSurveyors();

        assertNotNull(insuranceSurveyors, "The returned list should not be null");

        assertEquals(1, insuranceSurveyors.size(), "The size of the returned list should be 1");
        assertEquals("im_isssss", insuranceSurveyors.get(0).getName(), "The name of the first insurance surveyor should be im_isssss");
        assertEquals("rmit-surveyor", insuranceSurveyors.get(0).getCompanyName(), "The name of the first insurance surveyor should be rmit-surveyor");

    }

    @Test
    public void testDeleteInsuranceSurveyorById() {
        int accountId = 6;

        InsuranceSurveyor insuranceSurveyor = insuranceSurveyorController.getInsuranceSurveyorByAccountId(accountId);

        assertNotNull(insuranceSurveyor, "The insurance surveyor should exist.");

        insuranceSurveyorController.deleteInsuranceSurveyorById(insuranceSurveyor.getId());

        InsuranceSurveyor deletedInsuranceSurveyor = insuranceSurveyorController.getInsuranceSurveyorByAccountId(accountId);

        assertNull(deletedInsuranceSurveyor, "The insurance surveyor should NOT exist.");
    }
}
