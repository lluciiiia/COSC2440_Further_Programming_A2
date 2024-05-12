package com.team2.a2.Facade;

import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Request.LoginRequest;

public interface AccountFacade {
    public Account login(LoginRequest request);
    Account getAccountByID(int accountID);
}
