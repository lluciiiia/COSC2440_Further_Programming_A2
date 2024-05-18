package com.team2.a2.Controller;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.FacadeImpl.InsuranceManagerFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.UpdateProviderRequest;

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

    public void deleteInsuranceManagerById(int id, int userAccountId) throws Exception { insuranceManagerFacade.deleteInsuranceManagerById(id, userAccountId); }

    public void createInsuranceManager(InsertInsuranceManagerRequest request, int userAccountId) throws Exception { insuranceManagerFacade.createInsuranceManager(request, userAccountId); }

    public InsuranceManager getInsuranceManagerById(int id) { return insuranceManagerFacade.getInsuranceManagerById(id); }

    public void updateInsuranceManager(UpdateProviderRequest request, int userAccountId) throws Exception { insuranceManagerFacade.updateInsuranceManager(request, userAccountId); }
}
