package com.team2.a2.Controller;


import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.FacadeImpl.InsuranceCardFacadeImpl;
import com.team2.a2.Request.InsertInsuranceCardRequest;

public class InsuranceCardController {

    private InsuranceCardFacade insuranceCardFacade;

    public InsuranceCardController() {
        this.insuranceCardFacade = new InsuranceCardFacadeImpl();
    }

    public void createInsuranceCard(InsertInsuranceCardRequest request) { this.insuranceCardFacade.createInsuranceCard(request); }
}