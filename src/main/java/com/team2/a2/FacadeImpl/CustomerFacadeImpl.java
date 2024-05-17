package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    CustomerRepository customerRepository;
    DependentRepository dependentRepository;
    PolicyOwnerRepository policyOwnerRepository;
    AccountRepository accountRepository;
    InsuranceCardRepository insuranceCardRepository;
    ClaimRepository claimRepository;
    ClaimFacade claimFacade;
    InsuranceCardFacade insuranceCardFacade;


    public CustomerFacadeImpl() {
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.customerRepository = new CustomerRepository();
        this.accountRepository = new AccountRepository();
        this.insuranceCardRepository = new InsuranceCardRepository();
        this.claimRepository = new ClaimRepository();
        this.claimFacade = new ClaimFacadeImpl();
        this.insuranceCardFacade = new InsuranceCardFacadeImpl();
    }

    @Override
    public List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerAccountId) {
        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(policyOwnerAccountId);
        if (policyOwner == null) return null;

        return customerRepository.getCustomersByPolicyOwnerId(policyOwner.getId());
    }

    @Override
    public List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId) {
        return customerRepository.getCustomersByPolicyOwnerId(policyOwnerId);
    }

    @Override
    public List<Customer> getAllPolicyHoldersByPolicyOwnerId(int policyOwnerId) {
        return customerRepository.getAllPolicyHoldersByPolicyOwnerId(policyOwnerId);
    }

    @Override
    public Customer getCustomerByAccountId(int accountID) {
        return customerRepository.getCustomerByAccountId(accountID);
    }

    public List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) throws Exception {
        Customer policyHolder = customerRepository.getCustomerByAccountId(policyHolderAccountId);
        if (policyHolder == null) throw new Exception("Policy holder doesn't exist");

        if (policyHolder.getType() != CustomerType.POLICY_HOLDER)
            throw new Exception("Policy holder's customer type mismatch");

        List<Dependent> dependents = dependentRepository.getDependentsByPolicyHolderId(policyHolder.getId());

        List<Customer> customers = new ArrayList<Customer>();

        for (Dependent dependent : dependents) {
            Customer customer = customerRepository.getCustomerById(dependent.getCustomerId());
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public Customer createCustomer(InsertCustomerRequest request) throws Exception {
        Customer customer = null;

        PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(request.getPolicyOwnerAccountId());
        if (policyOwner == null) throw new Exception("Policy owner doesn't exist");

        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) throw new Exception("Someone else is using the username. Please use a different username");

        AccountType accountType = request.getType() == CustomerType.POLICY_HOLDER ? AccountType.POLICY_HOLDER : AccountType.DEPENDENT;

        if (request.getType() == CustomerType.POLICY_HOLDER) {

            Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), accountType);
            if (account == null) throw new Exception("Account hasn't been created");

            customer = customerRepository.createCustomer(request, account.getId(), policyOwner.getId());
            if (customer == null) throw new Exception("Customer hasn't been created");
        }
        else if (request.getType() == CustomerType.DEPENDENT) {
            Customer policyHolder = customerRepository.getCustomerById(request.getPolicyHolderId());
            if (policyHolder == null || policyHolder.getType() != CustomerType.POLICY_HOLDER)
                throw new Exception("Policy holder doesn't exist or the customer type mismatch of the policy holder");

            Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), accountType);
            if (account == null) throw new Exception("Account hasn't been created");

            customer = customerRepository.createCustomer(request, account.getId(), policyOwner.getId());
            if (customer == null) throw new Exception("Customer hasn't been created");

            dependentRepository.createDependent(customer.getId(), policyHolder.getId());
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer updateCustomer(UpdateCustomerRequest request) throws Exception {
        Customer customer = customerRepository.getCustomerById(request.getId());
        if (customer == null) throw new Exception("Customer doesn't exist");

        return customerRepository.updateCustomer(request);
    }

    @Override
    public void deleteCustomerById(int id) throws Exception {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) throw new Exception("Customer doesn't exist");

        if (customer.getType() == CustomerType.POLICY_HOLDER) {
            List<Dependent> dependents = dependentRepository.getDependentsByPolicyHolderId(id);

            for (Dependent dependent : dependents) {
                dependentRepository.deleteDependentById(dependent.getId());
            }

        } else if (customer.getType() == CustomerType.DEPENDENT) {
            Dependent dependent = dependentRepository.getDependentByCustomerId(id);

            if (dependent != null) {
                dependentRepository.deleteDependentById(dependent.getId());
            }
        }

        InsuranceCard insuranceCard = insuranceCardRepository.getInsuranceCardByCustomerId(id);
        if (insuranceCard != null) {
            insuranceCardFacade.deleteInsuranceCardById(insuranceCard.getId());
        }

        List<Claim> claims = claimRepository.getClaimsByCustomerId(id);

        for (Claim claim : claims) {
            claimFacade.deleteClaimById(claim.getId());
        }

        customerRepository.deleteCustomerById(id);
    }

}
