package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Facade.PolicyOwnerFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.Log;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import java.util.List;

public class PolicyOwnerFacadeImpl implements PolicyOwnerFacade {

    PolicyOwnerRepository policyOwnerRepository;
    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    CustomerFacade customerFacade;
    LogRepository logRepository;

    public PolicyOwnerFacadeImpl() {

        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.accountRepository = new AccountRepository();
        this.customerRepository = new CustomerRepository();
        this.customerFacade = new CustomerFacadeImpl();
        this.logRepository = new LogRepository();
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
    public void createPolicyOwner(InsertPolicyOwnerRequest request, int userAccountId) throws Exception {
        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) throw new Exception("Username is being used. Please try a different username");

        Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), AccountType.POLICY_OWNER);
        if (account == null) throw new Exception("Account hasn't been created");

        policyOwnerRepository.createPolicyOwner(request, account.getId());

        logRepository.createLog(userAccountId, "Created a policy owner");
    }

    @Override
    public void deletePolicyOwnerById(int id, int userAccountId) throws Exception {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerById(id);
        if (policyOwner == null) throw new Exception("Policy owner doesn't exist");

        int accountId = policyOwner.getAccountId();

        List<Customer> customers = customerRepository.getCustomersByPolicyOwnerId(policyOwner.getId());

        for (Customer customer : customers) {
            customerFacade.deleteCustomerById(customer.getId(), userAccountId);
        }

        policyOwnerRepository.deletePolicyOwnerById(id);
        accountRepository.deleteAccountById(accountId);

        logRepository.createLog(userAccountId, "Deleted a policy owner with id " + id);
    }

    @Override
    public PolicyOwner getPolicyOwnerById(int id) {
        return policyOwnerRepository.getPolicyOwnerById(id);
    }

    @Override
    public void updatePolicyOwner(UpdatePolicyOwnerRequest request, int userAccountId) throws Exception {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerById(request.getId());
        if (policyOwner == null) throw new Exception("Policy owner doesn't exist");

        policyOwnerRepository.updatePolicyOwner(request);

        logRepository.createLog(userAccountId, "Updated a policy owner with id " + request.getId());
    }
}
