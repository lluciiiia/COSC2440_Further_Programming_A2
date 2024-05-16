package com.team2.a2.Controller;

import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.FacadeImpl.AccountFacadeImpl;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdateAccountRequest;

import java.util.List;

public class AccountController {
    private AccountFacade accountFacade;

    public AccountController() {
        this.accountFacade = new AccountFacadeImpl();
    }

    public Account login(LoginRequest request) { return accountFacade.login(request); }
    public Account getAccountByID(int accountID) {
        return accountFacade.getAccountByID(accountID);
    }
    public Account getAccountByCustomerID(int customerID) {
        return accountFacade.getAccountByID(customerID);
    }
    public List<Account> getAllAccounts() { return accountFacade.getAllAccounts(); }

    public void updateAccount(UpdateAccountRequest request) { accountFacade.updateAccount(request); }
}
