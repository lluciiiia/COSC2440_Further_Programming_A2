package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    CustomerRepository customerRepository;
    DependentRepository dependentRepository;
    PolicyOwnerRepository policyOwnerRepository;

    public CustomerFacadeImpl() {
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerAccountId) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(policyOwnerAccountId);
        if (policyOwner == null) return null;

        return customerRepository.getCustomersByPolicyOwnerId(policyOwner.getId());
    }

    @Override
    public List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) {
        Customer policyHolder = customerRepository.getCustomerByAccountId(policyHolderAccountId);
        if (policyHolder == null) return null;

        if (policyHolder.getType() != CustomerType.POLICY_HOLDER) return null;

        List<Dependent> dependents = dependentRepository.getDependentsByPolicyHolderId(policyHolder.getId());

        List<Customer> customers = new ArrayList<Customer>();

        for (Dependent dependent : dependents) {
            Customer customer = customerRepository.getCustomerById(dependent.getCustomerId());
            customers.add(customer);
        }

        return customers;
    }

}
