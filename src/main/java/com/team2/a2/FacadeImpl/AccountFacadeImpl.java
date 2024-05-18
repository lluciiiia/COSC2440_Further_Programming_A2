package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.*;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Admin;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.*;
import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Request.UpdatePasswordRequest;

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

    LogRepository logRepository;

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
        this.logRepository = new LogRepository();
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
    public Account updatePassword(UpdatePasswordRequest request, int userAccountId) throws Exception {
        Account account = accountRepository.getAccountById(request.getId());
        if (account == null) throw new Exception("Account doesn't exist");

        logRepository.createLog(userAccountId, "Updated password of an account with id " + account.getId());

        return accountRepository.updatePassword(request);
    }

    @Override
    public Account getAccountByCustomerID(int customerID) throws Exception {
        Customer customer = customerRepository.getCustomerById(customerID);
        if (customer == null) throw new Exception("Customer doesn't exist");

        return accountRepository.getAccountById(customer.getAccountId());
    }

    @Override
    public void deleteAccountById(int id, int userAccountId) throws Exception {
        Account account = accountRepository.getAccountById(id);
        if (account == null) throw new Exception("Account doesn't exist");

        switch (account.getType()) {
            case ADMIN:
                Admin admin = adminRepository.getAdminByAccountId(id);
                if (admin == null) throw new Exception("Admin doesn't exist");

                adminRepository.deleteAdminById(admin.getId());
                break;
            case POLICY_HOLDER:
            case DEPENDENT:
                Customer customer = customerRepository.getCustomerByAccountId(id);
                if (customer == null) throw new Exception("Customer doesn't exist");

                customerFacade.deleteCustomerById(customer.getId(), userAccountId);
                break;
            case INSURANCE_MANAGER:
                InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerByAccountId(id);
                if (insuranceManager == null) throw new Exception("Insurance manager doesn't exist");

                insuranceManagerFacade.deleteInsuranceManagerById(insuranceManager.getId(), userAccountId);
                break;
            case POLICY_OWNER:
                PolicyOwner policyOwner = policyOwnerRepository.getPolicyOwnerByAccountId(id);
                if (policyOwner == null) throw new Exception("Policy owner doesn't exist");

                policyOwnerFacade.deletePolicyOwnerById(policyOwner.getId(), userAccountId);
                break;
            case INSURANCE_SURVEYOR:
                InsuranceSurveyor insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(id);
                if (insuranceSurveyor == null) throw new Exception("Insurance Surveyor doesn't exist");

                insuranceSurveyorFacade.deleteInsuranceSurveyorById(insuranceSurveyor.getId(), userAccountId);
                break;
            default:
                throw new Exception("Invalid Account Type");
        }

        accountRepository.deleteAccountById(id);

        logRepository.createLog(userAccountId, "Deleted an account with id " + id);
    }

}
