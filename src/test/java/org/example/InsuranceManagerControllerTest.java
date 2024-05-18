package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.InsuranceManagerController;
import com.team2.a2.Controller.InsuranceSurveyorController;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdateProviderRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceManagerControllerTest {

    private InsuranceManagerController insuranceManagerController;
    private InsuranceSurveyorController insuranceSurveyorController;

    private AccountController accountController;


    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceManagerController = new InsuranceManagerController();
        insuranceSurveyorController = new InsuranceSurveyorController();
        accountController = new AccountController();

    }

    @Test
    public void testGetInsuranceManagerByAccountId() {
        int accountId = 5;

        InsuranceManager expectedInsuranceManager = new InsuranceManager(2, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
                accountId, "rmit", "22 DaNang", "090", "123@gmail.com", "im_ismmm");

        InsuranceManager actualInsuranceManager = insuranceManagerController.getInsuranceManagerByAccountId(accountId);

        assertNotNull(actualInsuranceManager, "The insurance manager should exist.");
        assertEquals(expectedInsuranceManager.getId(), actualInsuranceManager.getId(), "The id should be the same.");
        assertEquals(expectedInsuranceManager.getName(), actualInsuranceManager.getName(), "The name should be the same.");
        assertEquals(expectedInsuranceManager.getCompanyName(), actualInsuranceManager.getCompanyName(), "The company name should be the same.");
        assertEquals(expectedInsuranceManager.getCompanyAddress(), actualInsuranceManager.getCompanyAddress(), "The address should be the same.");

    }

    @Test
    public void testGetAllInsuranceManagers() {
        List<InsuranceManager> insuranceManagers = insuranceManagerController.getAllInsuranceManagers();

        assertNotNull(insuranceManagers, "The returned list should not be null");

        assertEquals(1, insuranceManagers.size(), "The size of the returned list should be 1");
        assertEquals("im_ismmm", insuranceManagers.get(0).getName(), "The name of the first insurance manager should be im_ismmm");
        assertEquals("rmit", insuranceManagers.get(0).getCompanyName(), "The name of the first insurance manager should be rmit");

    }

    @Test
    public void testDeleteInsuranceManagerById() {
        int accountId = 5;

        InsuranceManager insuranceManager = insuranceManagerController.getInsuranceManagerByAccountId(accountId);

        assertNotNull(insuranceManager, "The insurance manager should exist.");

        insuranceManagerController.deleteInsuranceManagerById(insuranceManager.getId());

        InsuranceManager deletedInsuranceManager = insuranceManagerController.getInsuranceManagerByAccountId(accountId);
        assertNull(deletedInsuranceManager, "The insurance manager should NOT exist.");

        List<InsuranceSurveyor> deletedSurveyors = insuranceSurveyorController.getInsuranceSurveyorsByManagerId(insuranceManager.getId());

        assertEquals(0, deletedSurveyors.size(), "The deleted surveyors size should be 0.");
    }

    @Test
    public void testCreateInsuranceManager() throws Exception {
        String username = "new im username";
        String password = "12345";

        InsertInsuranceManagerRequest request = new InsertInsuranceManagerRequest(username, password, "rmit", "hcm", "2134567589", "12435@gmail.com", "im imim");

        insuranceManagerController.createInsuranceManager(request);

        Account createdAccount = accountController.login(new LoginRequest(username, password));
        InsuranceManager createdInsuranceManager = insuranceManagerController.getInsuranceManagerByAccountId(createdAccount.getId());

        assertNotNull(createdAccount, "CreatedAccount should NOT be null.");
        assertNotNull(createdInsuranceManager, "CreatedInsuranceManager should NOT be null.");
    }

    @Test
    public void testGetInsuranceManagerById() {
        int insuranceManagerId = 3;

        InsuranceManager insuranceManager = insuranceManagerController.getInsuranceManagerById(insuranceManagerId);

        assertNotNull(insuranceManager, "Insurance Manager should not be null");
        assertEquals(insuranceManagerId, insuranceManager.getId(), "ID should match");
      }

    @Test
    public void testUpdateInsuranceManager() throws Exception {
        int id = 3;

        UpdateProviderRequest request = new UpdateProviderRequest(
                id,
                "New Company2",
                "New Address2",
                "09876543212",
                "new2@example.com",
                "New Name2"
        );

        insuranceManagerController.updateInsuranceManager(request);

        InsuranceManager updatedInsuranceManager = insuranceManagerController.getInsuranceManagerById(id);

        assertEquals(request.getCompanyName(), updatedInsuranceManager.getCompanyName(), "Company name should be updated");
        assertEquals(request.getCompanyAddress(), updatedInsuranceManager.getCompanyAddress(), "Company address should be updated");
        assertEquals(request.getPhoneNumber(), updatedInsuranceManager.getPhoneNumber(), "Phone number should be updated");
        assertEquals(request.getEmail(), updatedInsuranceManager.getEmail(), "Email should be updated");
        assertEquals(request.getName(), updatedInsuranceManager.getName(), "Name should be updated");
    }

}
