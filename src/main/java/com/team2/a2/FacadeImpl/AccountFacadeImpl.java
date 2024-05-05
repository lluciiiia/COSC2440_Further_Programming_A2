package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Admin;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyHolder;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.LoginRequest;

public class AccountFacadeImpl implements AccountFacade {

    AccountRepository accountRepository;
    PolicyHolderRepository policyHolderRepository;
    DependentRepository dependentRepository;

    PolicyOwnerRepository policyOwnerRepository;
    AdminRepository adminRepository;


    public AccountFacadeImpl() {
        this.accountRepository = new AccountRepository();
        this.adminRepository = new AdminRepository();
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.policyHolderRepository = new PolicyHolderRepository();
    }

    public boolean login(LoginRequest request) {
        Account account = accountRepository.getAccount(request.getUsername(), request.getPassword());

        if (account == null) return false;

        return createSubAccountObject(account);
    }

    public boolean createSubAccountObject(Account account) {
        AccountType accountType = account.getType();

        switch(accountType) {
            case POLICY_HOLDER:
                PolicyHolder policyHolder = policyHolderRepository.getPolicyHolderByAccountId(account.getId());

            case DEPENDENT:
                  Dependent dependent = dependentRepository.getDependentByAccountId(account.getId());

            case POLICY_OWNER:
                PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(account.getId());

            case ADMIN:
                Admin admin = adminRepository.getAdminByAccountId(account.getId());


        }
        return true;

    }

}
