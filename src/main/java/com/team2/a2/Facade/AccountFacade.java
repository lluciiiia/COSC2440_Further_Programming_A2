package com.team2.a2.Facade;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdatePasswordRequest;

import java.util.List;

public interface AccountFacade {
    public Account login(LoginRequest request);
    Account getAccountByID(int accountID);

    List<Account> getAllAccounts();

    Account updatePassword(UpdatePasswordRequest request, int userAccountId) throws Exception;

    Account getAccountByCustomerID(int customerID) throws Exception;

    void deleteAccountById(int id, int userAccountId) throws Exception;
}
