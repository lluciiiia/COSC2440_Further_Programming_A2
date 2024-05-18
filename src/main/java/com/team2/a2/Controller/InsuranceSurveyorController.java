package com.team2.a2.Controller;

import com.team2.a2.Facade.InsuranceSurveyorFacade;
import com.team2.a2.FacadeImpl.InsuranceSurveyorFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Model.User.Provider.InsuranceSurveyor;
import com.team2.a2.Request.InsertInsuranceSurveyorRequest;
import com.team2.a2.Request.UpdateProviderRequest;

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

    public List<InsuranceSurveyor> getAllInsuranceSurveyors() {
        return insuranceSurveyorFacade.getAllInsuranceSurveyors();
    }

    public void deleteInsuranceSurveyorById(int id) { insuranceSurveyorFacade.deleteInsuranceSurveyorById(id); }

    public void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request) throws Exception { insuranceSurveyorFacade.createInsuranceSurveyor(request); }

    public void updateInsuranceSurveyor(UpdateProviderRequest request) throws Exception { insuranceSurveyorFacade.updateInsuranceSurveyor(request); }

    public InsuranceSurveyor getInsuranceSurveyorById(int id) { return insuranceSurveyorFacade.getInsuranceSurveyorById(id);
    }
}
