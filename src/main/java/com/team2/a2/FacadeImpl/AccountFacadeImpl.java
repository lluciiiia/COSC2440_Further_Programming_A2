package com.team2.a2.FacadeImpl;

import com.team2.a2.DependentInformationView;
import com.team2.a2.Facade.AccountFacade;
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

public class AccountFacadeImpl implements AccountFacade {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;
    DependentRepository dependentRepository;

    PolicyOwnerRepository policyOwnerRepository;
    AdminRepository adminRepository;

    InsuranceManagerRepository insuranceManagerRepository;
    InsuranceSurveyorRepository insuranceSurveyorRepository;

    public AccountFacadeImpl() {
        this.accountRepository = new AccountRepository();
        this.adminRepository = new AdminRepository();
        this.policyOwnerRepository = new PolicyOwnerRepository();
        this.dependentRepository = new DependentRepository();
        this.customerRepository = new CustomerRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
    }

    public Account login(LoginRequest request) {
        return accountRepository.getAccount(request.getUsername(), request.getPassword());
    }

    @Override
    public AccountType getAccountType(String username, String password) {
        Account account = accountRepository.getAccount(username, password);
        if (account != null) {
            return account.getType();
        }
        return null;
    }

    @Override
    public Account getAccountByID(int accountID) {
        return accountRepository.getAccountById(accountID);
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
