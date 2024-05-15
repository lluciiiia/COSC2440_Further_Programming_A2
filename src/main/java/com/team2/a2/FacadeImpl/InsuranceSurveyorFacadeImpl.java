package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceSurveyorFacade;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;

import java.util.List;

public class InsuranceSurveyorFacadeImpl implements InsuranceSurveyorFacade {
    InsuranceSurveyorRepository insuranceSurveyorRepository;

    public InsuranceSurveyorFacadeImpl() {
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();
    }

    @Override
    public InsuranceSurveyor getInsuranceSurveyorByAccountId(int accountId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorByAccountId(accountId);
    }


    @Override
    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId) {
        return insuranceSurveyorRepository.getInsuranceSurveyorByManagerID(managerId);
    }

    @Override
    public List<InsuranceSurveyor> getAllInsuranceSurveyors() {
        return insuranceSurveyorRepository.getAllInsuranceSurveyors();
    }
}
