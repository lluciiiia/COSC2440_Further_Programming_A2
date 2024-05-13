package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import org.junit.jupiter.api.*;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerTest {

    private AccountController accountController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        accountController = new AccountController(); // No need to pass mockAccountFacade here if you're not using it
    }

    @Test
    public void testLogin() {

        LoginRequest loginRequest = new LoginRequest("im_dependent", "12345");
        Account actualTrueAccount = accountController.login(loginRequest);

        // True Case
        assertEquals(2, actualTrueAccount.getId());

        // False Case
        assertNotEquals(3, actualTrueAccount.getId());

    }

    @Test
    public void testGetAccountByID() {
        int accountID = 2;
        Account expectedAccount = new Account(accountID, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
                "im_dependent", "12345", AccountType.DEPENDENT);

        Account notExpectedAccount = new Account(3, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
                "im_admin", "12345", AccountType.ADMIN);

        Account actualAccount = accountController.getAccountByID(accountID);

        // True Case
        assertEquals(expectedAccount.getId(), actualAccount.getId());
        assertEquals(expectedAccount.getUsername(), actualAccount.getUsername());
        assertEquals(expectedAccount.getType(), actualAccount.getType());

        // False Case
        assertNotEquals(notExpectedAccount.getId(), actualAccount.getId());
        assertNotEquals(notExpectedAccount.getUsername(), actualAccount.getUsername());
        assertNotEquals(notExpectedAccount.getType(), actualAccount.getType());
    }
}
