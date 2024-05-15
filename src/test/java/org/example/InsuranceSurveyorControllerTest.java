package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceSurveyorControllerTest {

    private InsuranceSurveyorController insuranceSurveyorController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceSurveyorController = new InsuranceSurveyorController();
    }

    @Test
    public void testGetAllInsuranceSurveyors() {
        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorController.getAllInsuranceSurveyors();

        assertNotNull(insuranceSurveyors, "The returned list should not be null");

        assertEquals(1, insuranceSurveyors.size(), "The size of the returned list should be 1");
        assertEquals("im_isssss", insuranceSurveyors.get(0).getName(), "The name of the first insurance surveyor should be im_isssss");
        assertEquals("rmit-surveyor", insuranceSurveyors.get(0).getCompanyName(), "The name of the first insurance surveyor should be rmit-surveyor");

    }
}
