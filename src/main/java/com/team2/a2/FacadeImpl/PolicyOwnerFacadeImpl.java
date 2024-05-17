package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Facade.PolicyOwnerFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.DependentRepository;
import com.team2.a2.Repository.PolicyOwnerRepository;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import java.util.List;

public class PolicyOwnerFacadeImpl implements PolicyOwnerFacade {

    PolicyOwnerRepository policyOwnerRepository;
    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    CustomerFacade customerFacade;

    public PolicyOwnerFacadeImpl() {

        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.accountRepository = new AccountRepository();
        this.customerRepository = new CustomerRepository();
        this.customerFacade = new CustomerFacadeImpl();
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

    @Override
    public void deletePolicyOwnerById(int id) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerById(id);
        if (policyOwner == null) return;

        int accountId = policyOwner.getAccountId();

        List<Customer> customers = customerRepository.getCustomersByPolicyOwnerId(policyOwner.getId());

        for (Customer customer : customers) {
            customerFacade.deleteCustomerById(customer.getId());
        }

        policyOwnerRepository.deletePolicyOwnerById(id);
        accountRepository.deleteAccountById(accountId);
    }

    @Override
    public PolicyOwner getPolicyOwnerById(int id) {
        return policyOwnerRepository.getPolicyOwnerById(id);
    }

    @Override
    public void updatePolicyOwner(UpdatePolicyOwnerRequest request) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerById(request.getId());
        if (policyOwner == null) return;

        policyOwnerRepository.updatePolicyOwner(request);
    }
}
