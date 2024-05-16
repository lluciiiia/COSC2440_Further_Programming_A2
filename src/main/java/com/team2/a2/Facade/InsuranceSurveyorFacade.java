package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

import java.util.List;

public interface InsuranceSurveyorFacade {
    InsuranceSurveyor getInsuranceSurveyorByAccountId(int id);

    List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId);

    List<InsuranceSurveyor> getAllInsuranceSurveyors();

    void deleteInsuranceSurveyorById(int id);
}
