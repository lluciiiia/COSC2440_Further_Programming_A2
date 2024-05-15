package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.PolicyOwnerFacade;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.DependentRepository;
import com.team2.a2.Repository.PolicyOwnerRepository;

import java.util.List;

public class PolicyOwnerFacadeImpl implements PolicyOwnerFacade {

    PolicyOwnerRepository policyOwnerRepository;

    public PolicyOwnerFacadeImpl() {
        this.policyOwnerRepository = new PolicyOwnerRepository();
    }

    @Override
    public PolicyOwner getPolicyOwnerByAccountId(int accountID) {
        return policyOwnerRepository.getPolicyOwnerByAccountId(accountID);
    }

    @Override
    public List<PolicyOwner> getAllPolicyOwners() {
        return policyOwnerRepository.getAllPolicyOwners();
    }
}
