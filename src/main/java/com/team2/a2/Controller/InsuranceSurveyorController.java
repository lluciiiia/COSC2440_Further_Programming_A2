package com.team2.a2.Controller;

import com.team2.a2.Facade.InsuranceSurveyorFacade;
import com.team2.a2.FacadeImpl.InsuranceSurveyorFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;

import java.util.List;

public class InsuranceSurveyorController {

    private InsuranceSurveyorFacade insuranceSurveyorFacade;

    public InsuranceSurveyorController() {
        this.insuranceSurveyorFacade = new InsuranceSurveyorFacadeImpl();
    }


    public InsuranceSurveyor getInsuranceSurveyorByAccountId(int id) {
        return insuranceSurveyorFacade.getInsuranceSurveyorByAccountId(id);
    }

    public List<InsuranceSurveyor> getInsuranceSurveyorsByManagerId(int managerId) {
        return insuranceSurveyorFacade.getInsuranceSurveyorsByManagerId(managerId);
    }
}
