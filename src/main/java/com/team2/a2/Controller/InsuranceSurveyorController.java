package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

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

    public void deleteInsuranceSurveyorById(int id, int userAccountId) throws Exception { insuranceSurveyorFacade.deleteInsuranceSurveyorById(id, userAccountId); }

    public void createInsuranceSurveyor(InsertInsuranceSurveyorRequest request, int userAccountId) throws Exception { insuranceSurveyorFacade.createInsuranceSurveyor(request, userAccountId); }

    public void updateInsuranceSurveyor(UpdateProviderRequest request, int userAccountId) throws Exception { insuranceSurveyorFacade.updateInsuranceSurveyor(request, userAccountId); }

    public InsuranceSurveyor getInsuranceSurveyorById(int id) { return insuranceSurveyorFacade.getInsuranceSurveyorById(id);
    }
}
