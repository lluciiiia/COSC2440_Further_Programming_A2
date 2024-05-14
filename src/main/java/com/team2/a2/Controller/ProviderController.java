package com.team2.a2.Controller;

import com.team2.a2.Facade.ProviderFacade;
import com.team2.a2.FacadeImpl.ProviderFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

public class ProviderController {
    private ProviderFacade providerFacade;

    public ProviderController() {
        this.providerFacade = new ProviderFacadeImpl();
    }

    public InsuranceSurveyor getInsuranceSurveyor(int id) {
        return providerFacade.getInsuranceSurveyorById(id);
    }
}
