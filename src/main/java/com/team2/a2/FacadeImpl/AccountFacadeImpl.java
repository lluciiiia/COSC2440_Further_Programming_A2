package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.*;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Admin;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdateAccountRequest;

import java.util.List;

public class AccountFacadeImpl implements AccountFacade {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    DependentRepository dependentRepository;
    PolicyOwnerRepository policyOwnerRepository;
    AdminRepository adminRepository;
    InsuranceManagerRepository insuranceManagerRepository;
    InsuranceSurveyorRepository insuranceSurveyorRepository;
    CustomerFacade customerFacade;
    InsuranceManagerFacade insuranceManagerFacade;
    PolicyOwnerFacade policyOwnerFacade;
    InsuranceSurveyorFacade insuranceSurveyorFacade;

    public AccountFacadeImpl() {
        this.accountRepository = new AccountRepository();
        this.adminRepository = new AdminRepository();
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.customerRepository = new CustomerRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
        this.customerFacade = new CustomerFacadeImpl();
        this.insuranceManagerFacade = new InsuranceManagerFacadeImpl();
        this.policyOwnerFacade = new PolicyOwnerFacadeImpl();
        this.insuranceSurveyorFacade = new InsuranceSurveyorFacadeImpl();
    }

    public Account login(LoginRequest request) {
        return accountRepository.getAccount(request.getUsername(), request.getPassword());
    }

    @Override
    public Account getAccountByID(int accountID) {
        return accountRepository.getAccountById(accountID);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public Account updateAccount(UpdateAccountRequest request) {
        Account account = accountRepository.getAccountById(request.getId());
        if (account == null) return null;

        return accountRepository.updateAccount(request);
    }

    @Override
    public Account getAccountByCustomerID(int customerID) {
        Customer customer = customerRepository.getCustomerById(customerID);
        if (customer == null) return null;

        return accountRepository.getAccountById(customer.getAccountId());
    }

    @Override
    public void deleteAccountById(int id) {
        Account account = accountRepository.getAccountById(id);
        if (account == null) return;

        switch (account.getType()) {
            case ADMIN:
                Admin admin = adminRepository.getAdminByAccountId(id);
                if (admin == null) return;

                adminRepository.deleteAdminById(admin.getId());
                break;
            case POLICY_HOLDER:
            case DEPENDENT:
                Customer customer = customerRepository.getCustomerByAccountId(id);
                if (customer == null) return;

                customerFacade.deleteCustomerById(customer.getId());
                break;
            case INSURANCE_MANAGER:
                InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerByAccountId(id);
                if (insuranceManager == null) return;

                insuranceManagerFacade.deleteInsuranceManagerById(insuranceManager.getId());
                break;
            case POLICY_OWNER:
                PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(id);
                if (policyOwner == null) return;

                policyOwnerFacade.deletePolicyOwnerById(policyOwner.getId());
                break;
            case INSURANCE_SURVEYOR:
                InsuranceSurveyor insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(id);
                if (insuranceSurveyor == null) return;

                insuranceSurveyorFacade.deleteInsuranceSurveyorById(insuranceSurveyor.getId());
                break;
            default:
                System.out.println("Invalid account type");
                break;
        }

        accountRepository.deleteAccountById(id);
    }

    public boolean createSubAccountObject(Account account) {
        AccountType accountType = account.getType();
        System.out.println(accountType);
        Customer customer;
        Dependent dependent;
        PolicyOwner policyOwner;
        Admin admin;
        InsuranceManager insuranceManager;
        InsuranceSurveyor insuranceSurveyor;
        switch(accountType) {
            case POLICY_HOLDER:
                customer = customerRepository.getCustomerByAccountId(account.getId());
                break;
            case DEPENDENT:
                customer = customerRepository.getCustomerByAccountId(account.getId());
                dependent = dependentRepository.getDependentByCustomerId(customer.getId());
//                System.out.println(customer.getName());
                break;
            case POLICY_OWNER:
                policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(account.getId());
                break;
            case ADMIN:
                admin = adminRepository.getAdminByAccountId(account.getId());
                break;
            case INSURANCE_MANAGER:
                insuranceManager = insuranceManagerRepository.getInsuranceManagerByAccountId(account.getId());
                break;
            case INSURANCE_SURVEYOR:
                insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(account.getId());
                break;
        }
        return true;
    }


}
