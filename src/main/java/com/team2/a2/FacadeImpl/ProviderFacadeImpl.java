package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ProviderFacade;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;

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
}
