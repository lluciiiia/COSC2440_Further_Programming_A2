package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Repository.InsuranceManagerRepository;
import com.team2.a2.Repository.InsuranceSurveyorRepository;

import java.util.List;

public class InsuranceManagerFacadeImpl implements InsuranceManagerFacade {

    InsuranceManagerRepository insuranceManagerRepository;
    InsuranceSurveyorRepository insuranceSurveyorRepository;

    public InsuranceManagerFacadeImpl() {

        this.insuranceManagerRepository = new InsuranceManagerRepository();
        this.insuranceSurveyorRepository = new InsuranceSurveyorRepository();

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
}
