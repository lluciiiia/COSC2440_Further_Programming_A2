package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    PolicyHolderRepository policyHolderRepository;
    DependentRepository dependentRepository;
    PolicyOwnerRepository policyOwnerRepository;

    public CustomerFacadeImpl() {
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.policyHolderRepository = new PolicyHolderRepository();
    }

    @Override
    public List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerAccountId) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(policyOwnerAccountId);
        if (policyOwner == null) return null;

        List<Customer> customers = new ArrayList<Customer>();

//        policyHolderRepository.getPolicyHoldersByPolicyOwnerId(policyOwner.getId());
//        dependentRepository.getDependentsByPolicyOwnerId(policyOwner.getId());



        return customers;
    }
}
