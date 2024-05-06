package com.team2.a2.Controller;

import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.FacadeImpl.AccountFacadeImpl;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;

public class AccountController {
    private AccountFacade accountFacade;

    // Constructor to initialize accountFacade
    public AccountController() {
        this.accountFacade = new AccountFacadeImpl();
    }

    public boolean login(LoginRequest request) {
        return accountFacade.login(request);
    }

    public AccountType getAccountType(String username, String password) {
        Account account = accountFacade.getAccount(username, password);
        if (account != null) {
            return account.getType();
        } else {
            return null;
        }
    }
}
