package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ProviderFacade;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;

import java.util.List;

public class ProviderFacadeImpl implements ProviderFacade {
    InsuranceSurveyorRepository insuranceSurveyorRepository;
    InsuranceManagerRepository insuranceManagerRepository;

    public ProviderFacadeImpl() {
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
        this.insuranceManagerRepository = new InsuranceManagerRepository();
    }


    @Override
    public InsuranceSurveyor getInsuranceSurveyorById(int accountId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(accountId);
    }

    @Override
    public InsuranceManager getInsuranceManagerById(int accountId) {
        return insuranceManagerRepository.getInsuranceManagerByAccountId(accountId);
    }

    @Override
    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorByManagerID(managerId);
    }
}
