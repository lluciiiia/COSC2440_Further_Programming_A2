package com.team2.a2.Controller;

import com.team2.a2.Facade.ProviderFacade;
import com.team2.a2.FacadeImpl.ProviderFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

import java.util.List;

public class ProviderController {
    private ProviderFacade providerFacade;

    public ProviderController() {
        this.providerFacade = new ProviderFacadeImpl();
    }

    public InsuranceSurveyor getInsuranceSurveyor(int id) {
        return providerFacade.getInsuranceSurveyorById(id);
    }

    public InsuranceManager getInsuranceManager(int id) {
        return providerFacade.getInsuranceManagerById(id);
    }
    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId) {
        return providerFacade.getInsuranceSurveyorsByManagerId(managerId);
    }
}
