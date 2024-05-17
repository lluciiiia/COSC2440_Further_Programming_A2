package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.UpdateProviderRequest;

import java.util.List;

public class InsuranceManagerFacadeImpl implements InsuranceManagerFacade {

    InsuranceManagerRepository insuranceManagerRepository;
    InsuranceSurveyorRepository insuranceSurveyorRepository;

    AccountRepository accountRepository;

    public InsuranceManagerFacadeImpl() {

        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
        this.accountRepository = new AccountRepository();

    }

    @Override
    public InsuranceManager getInsuranceManagerByAccountId(int accountId) {
        return insuranceManagerRepository.getInsuranceManagerByAccountId(accountId);
    }

    @Override
    public List<InsuranceManager> getAllInsuranceManagers() {
        return insuranceManagerRepository.getAllInsuranceManagers();
    }

    @Override
    public void deleteInsuranceManagerById(int id) {

        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorRepository.getInsuranceSurveyorsByManagerID(id);

        for (InsuranceSurveyor surveyor : insuranceSurveyors) {
            insuranceSurveyorRepository.deleteInsuranceSurveyorById(surveyor.getId());
        }

        insuranceManagerRepository.deleteInsuranceManagerById(id);

    }

    @Override
    public void createInsuranceManager(InsertInsuranceManagerRequest request) throws Exception {
        Account existingAccount = accountRepository.getAccountByUsername(request.getUsername());
        if (existingAccount != null) throw new Exception("Username is being used. Please try a different username");

            Account account = accountRepository.createAccount(request.getUsername(), request.getPassword(), AccountType.INSURANCE_MANAGER);
            if (account == null) throw new Exception("Account hasn't been created");

            insuranceManagerRepository.createInsuranceManager(request, account.getId());
    }

    @Override
    public InsuranceManager getInsuranceManagerById(int id) {
        return insuranceManagerRepository.getInsuranceManagerById(id);
    }

    @Override
    public void updateInsuranceManager(UpdateProviderRequest request) throws Exception {
        InsuranceManager insuranceManager = insuranceManagerRepository.getInsuranceManagerById(request.getId());
        if (insuranceManager == null) throw new Exception("Insurance manager doesn't exist");

        insuranceManagerRepository.updateInsuranceManager(request);
    }
}
