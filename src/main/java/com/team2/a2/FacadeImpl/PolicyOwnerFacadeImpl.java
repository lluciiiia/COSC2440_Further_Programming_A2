package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.PolicyOwnerFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.DependentRepository;
import com.team2.a2.Repository.PolicyOwnerRepository;
import com.team2.a2.Request.InsertPolicyOwnerRequest;

import java.util.List;

public class PolicyOwnerFacadeImpl implements PolicyOwnerFacade {

    PolicyOwnerRepository policyOwnerRepository;
    AccountRepository accountRepository;

    public PolicyOwnerFacadeImpl() {

        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.accountRepository = new AccountRepository();
    }

    @Override
    public PolicyOwner getPolicyOwnerByAccountId(int accountID) {
        return policyOwnerRepository.getPolicyOwnerByAccountId(accountID);
    }

    @Override
    public List<PolicyOwner> getAllPolicyOwners() {
        return policyOwnerRepository.getAllPolicyOwners();
    }

    @Override
    public void createPolicyOwner(InsertPolicyOwnerRequest request) {

        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) return;

        Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), AccountType.POLICY_OWNER);
        if (account == null) return;

        policyOwnerRepository.createPolicyOwner(request, account.getId());
    }
}
