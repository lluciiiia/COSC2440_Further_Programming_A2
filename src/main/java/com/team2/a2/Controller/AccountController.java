package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */


import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.FacadeImpl.AccountFacadeImpl;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdatePasswordRequest;

import java.util.List;

public class AccountController {
    private AccountFacade accountFacade;

    public AccountController() {
        this.accountFacade = new AccountFacadeImpl();
    }

    public Account login(LoginRequest request) { return accountFacade.login(request); }
    public Account getAccountByID(int id) {
        return accountFacade.getAccountByID(id);
    }
    public Account getAccountByCustomerID(int customerID) throws Exception {
        return accountFacade.getAccountByCustomerID(customerID);
    }
    public List<Account> getAllAccounts() { return accountFacade.getAllAccounts(); }
    public Account updatePassword(UpdatePasswordRequest request, int userAccountId) throws Exception { return accountFacade.updatePassword(request, userAccountId); }

    public void deleteAccountById(int id, int userAccountId) throws Exception { accountFacade.deleteAccountById(id, userAccountId); }
}
