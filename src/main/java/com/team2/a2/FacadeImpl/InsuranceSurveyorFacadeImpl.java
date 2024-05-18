package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceSurveyorFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;
import com.team2.a2.Repository.LogRepository;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;
import com.team2.a2.Request.UpdateProviderRequest;

import java.util.List;

public class InsuranceSurveyorFacadeImpl implements InsuranceSurveyorFacade {
    InsuranceSurveyorRepository insuranceSurveyorRepository;
    InsuranceManagerRepository insuranceManagerRepository;
    AccountRepository accountRepository;

    LogRepository logRepository;

    public InsuranceSurveyorFacadeImpl() {
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.accountRepository = new AccountRepository();
        this.logRepository = new LogRepository();
    }

    @Override
    public InsuranceSurveyor getInsuranceSurveyorByAccountId(int accountId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(accountId);
    }


    @Override
    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorsByManagerID(managerId);
    }

    @Override
    public List<InsuranceSurveyor> getAllInsuranceSurveyors() {
        return insuranceSurveyorRepository.getAllInsuranceSurveyors();
    }

    @Override
    public void deleteInsuranceSurveyorById(int id, int userAccountId) throws Exception {
        InsuranceSurveyor insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorById(id);
        if (insuranceSurveyor == null) throw new Exception("Insurance surveyor doesn't exist");
        int accountId = insuranceSurveyor.getAccountId();
        insuranceSurveyorRepository.deleteInsuranceSurveyorById(id);
        accountRepository.deleteAccountById(accountId);

        logRepository.createLog(userAccountId, "Deleted an insurance surveyor with id " + id);
    }

    @Override
    public void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request, int userAccountId) throws Exception {
        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) throw new Exception("Username is being used. Please try a different username");

        InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerById(request.getInsuranceManagerId());
        if (insuranceManager == null) throw new Exception("Insurance manager doesn't exist");

        Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), AccountType.INSURANCE_SURVEYOR);
        if (account == null) throw new Exception("Account doesn't exist");

        insuranceSurveyorRepository.createInsuranceSurveyor(request, account.getId());

        logRepository.createLog(userAccountId, "Created an insurance surveyor");
    }

    @Override
    public void updateInsuranceSurveyor(UpdateProviderRequest request, int userAccountId) throws Exception {
        InsuranceSurveyor insuranceSurveyor = insuranceSurveyorRepository.getInsuranceSurveyorById(request.getId());
        if (insuranceSurveyor == null) throw new Exception("Insurance surveyor doesn't exist");

        insuranceSurveyorRepository.updateInsuranceSurveyor(request);

        logRepository.createLog(userAccountId, "Updated an insurance surveyor with id " + request.getId());
    }

    @Override
    public InsuranceSurveyor getInsuranceSurveyorById(int id) {
        return insuranceSurveyorRepository.getInsuranceSurveyorById(id);
    }
}
