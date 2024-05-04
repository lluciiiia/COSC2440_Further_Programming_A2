package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Request.LoginRequest;

public class AccountFacadeImpl implements AccountFacade {

    AccountRepository accountRepository;

    public boolean login(LoginRequest request) {
        Account account = accountRepository.getAccount(request.getUsername(), request.getPassword());

        if (account == null) return false;

        return createSubAccountObject(account);
    }

    public boolean createSubAccountObject(Account account) {
        AccountType accountType = account.getType();

//        switch(accountType) {
//            case AccountType.POLICY_HOLDER:
//                PolicyHolder policyHolder = policyHolderRepository.getPolicyHoler();
//
//
//        }
        return true;


    }

}
