package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.AccountController;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdatePasswordRequest;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountControllerTest {

    private AccountController accountController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        accountController = new AccountController();
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

    @Test
    public void testGetAccountByCustomerID() throws Exception {
        int customerId = 6;

        Account expectedAccount = new Account(37, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                "please5@adsf.com", "12345", AccountType.DEPENDENT);

        Account actualAccount = accountController.getAccountByCustomerID(customerId);

        assertEquals(expectedAccount.getId(), actualAccount.getId(), "Id should be the same.");
        assertEquals(expectedAccount.getUsername(), actualAccount.getUsername(), "Username should be the same.");
        assertEquals(expectedAccount.getType(), actualAccount.getType(), "AccountType should be the same.");
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = accountController.getAllAccounts();

        assertNotNull(accounts, "The returned list should not be null");

        assertNotEquals(1, accounts.size(), "The size of the returned list should NOT be 1");
        assertEquals("im_dependent", accounts.get(0).getUsername(), "The username of the first account should be im_dependent");
        assertEquals(AccountType.DEPENDENT, accounts.get(0).getType(), "The type of the first account should be DEPENDENT");
    }

    @Test
    public void testUpdatePassword() throws Exception {
        int id = 40;
        int userAccountId = 1;

        Account currentAccount = accountController.getAccountByID(id);

        UpdatePasswordRequest request = new UpdatePasswordRequest(id, "12345678");
        accountController.updatePassword(request, userAccountId);

        Account updatedAccount = accountController.getAccountByID(id);

        assertNotEquals(currentAccount.getPassword(), updatedAccount.getPassword(), "The password between currentAccount and updatedAccount should NOT be the same.");
    }

    @Test
    public void testDeleteAccountById() throws Exception {
        int id = 1;
        int userAccountId = 1;

        accountController.deleteAccountById(id, userAccountId);

        Account deletedAccount = accountController.getAccountByID(id);
        assertNull(deletedAccount, "The account should be deleted");
    }
}
