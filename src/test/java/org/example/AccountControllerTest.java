package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.AccountController;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private AccountFacade mockAccountFacade;

    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        ConnectionManager.initConnection();
        accountController = new AccountController(); // No need to pass mockAccountFacade here if you're not using it
    }

    @Test
    public void testLogin() {

        LoginRequest loginRequest = new LoginRequest("im_dependent", "12345");
        Account actualTrueAccount = accountController.login(loginRequest);

        // True Case
        assertSame(2, actualTrueAccount.getId());

        // False Case
        assertNotSame(3, actualTrueAccount.getId());

    }

    @Test
    public void testGetAccountByID() {
        // Arrange
//        int accountID = 1;
//        Account expectedAccount = new Account(accountID, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
//                "im_dependent", "12345", AccountType.DEPENDENT);
//
//        // Mock behavior of AccountFacade
//        when(accountController.getAccountByID(accountID)).thenReturn(expectedAccount);
//
//        // Act
//        Account actualAccount = accountController.getAccountByID(accountID);
//
//        // Assert
//        assertEquals(expectedAccount, actualAccount);
    }
}
