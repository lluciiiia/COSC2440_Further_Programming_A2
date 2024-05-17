package com.team2.a2.Controller;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.FacadeImpl.InsuranceManagerFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.UpdateInsuranceManagerRequest;

import java.util.List;

public class InsuranceManagerController {

    private InsuranceManagerFacade insuranceManagerFacade;

    public InsuranceManagerController() { this.insuranceManagerFacade = new InsuranceManagerFacadeImpl(); }

    public InsuranceManager getInsuranceManagerByAccountId(int id) {
        return insuranceManagerFacade.getInsuranceManagerByAccountId(id);
    }

    public List<InsuranceManager> getAllInsuranceManagers() {
        return insuranceManagerFacade.getAllInsuranceManagers();
    }

    public void deleteInsuranceManagerById(int id) { insuranceManagerFacade.deleteInsuranceManagerById(id); }

    public void createInsuranceManager(InsertInsuranceManagerRequest request) { insuranceManagerFacade.createInsuranceManager(request); }

    public InsuranceManager getInsuranceManagerById(int id) { return insuranceManagerFacade.getInsuranceManagerById(id); }

    public void updateInsuranceManager(UpdateInsuranceManagerRequest request) { insuranceManagerFacade.updateInsuranceManager(request); }
}
