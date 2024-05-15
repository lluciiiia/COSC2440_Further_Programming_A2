package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;

public class InsuranceManagerFacadeImpl implements InsuranceManagerFacade {

    InsuranceManagerRepository insuranceManagerRepository;

    public InsuranceManagerFacadeImpl() {
        this.insuranceManagerRepository = new InsuranceManagerRepository();
    }

    @Override
    public InsuranceManager getInsuranceManagerByAccountId(int accountId) {
        return insuranceManagerRepository.getInsuranceManagerByAccountId(accountId);
    }
}
