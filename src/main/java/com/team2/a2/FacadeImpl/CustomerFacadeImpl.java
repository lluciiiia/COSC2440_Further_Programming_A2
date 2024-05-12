package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.CustomerType;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.UpsertCustomerRequest;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    CustomerRepository customerRepository;
    DependentRepository dependentRepository;
    PolicyOwnerRepository policyOwnerRepository;
    AccountRepository accountRepository;

    public CustomerFacadeImpl() {
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.customerRepository = new CustomerRepository();
        this.accountRepository = new AccountRepository();
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

    @Override
    public Customer createCustomer(UpsertCustomerRequest request) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(request.getPolicyOwnerAccountId());
        if (policyOwner == null) return null;

        // TODO: check if the account exists
        // Determine the account type based on the customer type
        AccountType accountType = request.getType() == CustomerType.POLICY_HOLDER ? AccountType.POLICY_HOLDER : AccountType.DEPENDENT;


        Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), accountType);
        System.out.println("account: ");
        System.out.println(account.getId());
        if (account == null) return null;

        Customer customer = customerRepository.createCustomer(request, account.getId(), policyOwner.getId());
        if (customer == null) return null;

        if (request.getType() == CustomerType.DEPENDENT) {
            Dependent dependent = dependentRepository.createDependent(customer.getId());
            if (dependent == null) return null;
        }
        return customer;
    }

}
