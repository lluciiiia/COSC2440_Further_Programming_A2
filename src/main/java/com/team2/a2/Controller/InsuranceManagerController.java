package com.team2.a2.Controller;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.FacadeImpl.InsuranceManagerFacadeImpl;
import com.team2.a2.Model.User.Provider.InsuranceManager;

public class InsuranceManagerController {

    private InsuranceManagerFacade insuranceManagerFacade;

    public InsuranceManagerController() { this.insuranceManagerFacade = new InsuranceManagerFacadeImpl(); }


    public InsuranceManager getInsuranceManagerByAccountId(int id) {
        return insuranceManagerFacade.getInsuranceManagerByAccountId(id);
    }




}
