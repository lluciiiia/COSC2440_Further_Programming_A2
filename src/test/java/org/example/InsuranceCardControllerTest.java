package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.AccountController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.InsertInsuranceCardRequest;
import java.sql.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsuranceCardControllerTest {

    private InsuranceCardController insuranceCardController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        insuranceCardController = new InsuranceCardController();
    }

    @Test
    public void testCreateInsuranceCard() {
        InsertInsuranceCardRequest request = new InsertInsuranceCardRequest(1,
                                                                "1234567890123456",
                                                                            Date.valueOf("2024-12-31"), "VPBank", "1234567890");
        insuranceCardController.createInsuranceCard(request);
    }
}

