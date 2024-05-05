package com.team2.a2.Facade;

import com.team2.a2.Model.User.Account;
import com.team2.a2.Request.LoginRequest;

public interface AccountFacade {
    public boolean login(LoginRequest request);

    Account getAccount(String username, String password);
}
