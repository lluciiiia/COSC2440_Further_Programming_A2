package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Admin;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyHolder;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.LoginRequest;

public class AccountFacadeImpl implements AccountFacade {

    AccountRepository accountRepository;
    PolicyHolderRepository policyHolderRepository;
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
        this.policyHolderRepository = new PolicyHolderRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
    }

    public boolean login(LoginRequest request) {
        Account account = accountRepository.getAccount(request.getUsername(), request.getPassword());

        if (account == null) return false;

        return createSubAccountObject(account);
    }

    @Override
    public Account getAccount(String username, String password) {
        return accountRepository.getAccount(username, password);
    }

    public boolean createSubAccountObject(Account account) {
        AccountType accountType = account.getType();

        switch(accountType) {
            case POLICY_HOLDER:
                PolicyHolder policyHolder = policyHolderRepository.getPolicyHolderByAccountId(account.getId());

            case DEPENDENT:
                  Dependent dependent = dependentRepository.getDependentByAccountId(account.getId());

            case POLICY_OWNER:
                PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(account.getId());

            case ADMIN:
                Admin admin = adminRepository.getAdminByAccountId(account.getId());

            case INSURANCE_MANAGER:
                InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerByAccountId(account.getId());

            case INSURANCE_SURVEYOR:
                InsuranceSurveyor insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(account.getId());

        }
        return true;

    }

}
