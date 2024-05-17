package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceSurveyorFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;

import java.util.List;

public class InsuranceSurveyorFacadeImpl implements InsuranceSurveyorFacade {
    InsuranceSurveyorRepository insuranceSurveyorRepository;
    InsuranceManagerRepository insuranceManagerRepository;
    AccountRepository accountRepository;

    public InsuranceSurveyorFacadeImpl() {
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.accountRepository = new AccountRepository();
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
    public void deleteInsuranceSurveyorById(int id) {
        insuranceSurveyorRepository.deleteInsuranceSurveyorById(id);
    }

    @Override
    public void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request) {
        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) return;

        InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerById(request.getInsuranceManagerId());
        if (insuranceManager == null) return;

        Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), AccountType.INSURANCE_SURVEYOR);
        if (account == null) return;

        insuranceSurveyorRepository.createInsuranceSurveyor(request, account.getId());
    }
}
