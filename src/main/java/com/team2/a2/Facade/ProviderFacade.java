package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

import java.util.List;

public interface ProviderFacade {
    public InsuranceSurveyor getInsuranceSurveyorByAccountId(int accountId);
    List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId);
}
