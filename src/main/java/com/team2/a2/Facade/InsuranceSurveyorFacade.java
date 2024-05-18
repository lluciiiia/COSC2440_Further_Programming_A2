package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;
import com.team2.a2.Request.UpdateProviderRequest;

import java.util.List;

public interface InsuranceSurveyorFacade {
    InsuranceSurveyor getInsuranceSurveyorByAccountId(int id);

    List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId);

    List<InsuranceSurveyor> getAllInsuranceSurveyors();

    void deleteInsuranceSurveyorById(int id, int userAccountId) throws Exception;

    void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request, int userAccountId) throws Exception;

    void updateInsuranceSurveyor(UpdateProviderRequest request, int userAccountId) throws Exception;

    InsuranceSurveyor getInsuranceSurveyorById(int id);
}
