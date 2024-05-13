package org.example;

import com.team2.a2.Controller.AccountController;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private AccountFacade mockAccountFacade;

    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountController = new AccountController(mockAccountFacade);
    }

    @Test
    public void testLogin() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("im_dependent", "12345");
        Account expectedAccount = new Account(1, Date.valueOf("2024-05-05"), Date.valueOf("2024-05-05"),
                "im_dependent", "12345", AccountType.DEPENDENT);

        // Mock behavior of AccountFacade
        when(mockAccountFacade.login(loginRequest)).thenReturn(expectedAccount);

        // Act
        Account actualAccount = accountController.login(loginRequest);

        // Assert
        assertEquals(expectedAccount, actualAccount);
    }

//    @Test
//    public void testGetAccountByID() {
//        // Arrange
//        int accountID = 1;
//        Account expectedAccount = new Account(accountID, "username", AccountType.CUSTOMER);
//
//        // Mock behavior of AccountFacade
//        when(mockAccountFacade.getAccountByID(accountID)).thenReturn(expectedAccount);
//
//        // Act
//        Account actualAccount = accountController.getAccountByID(accountID);
//
//        // Assert
//        assertEquals(expectedAccount, actualAccount);
//    }
}
