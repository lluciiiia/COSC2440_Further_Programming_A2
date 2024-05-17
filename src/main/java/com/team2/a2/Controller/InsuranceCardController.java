package com.team2.a2.Controller;


import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.FacadeImpl.InsuranceCardFacadeImpl;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Request.InsertInsuranceCardRequest;

public class InsuranceCardController {

    private InsuranceCardFacade insuranceCardFacade;

    public InsuranceCardController() {
        this.insuranceCardFacade = new InsuranceCardFacadeImpl();
    }

    public InsuranceCard getInsuranceCardByCustomerID(int customerID) {
        return insuranceCardFacade.getInsuranceCardByCustomerID(customerID);
    }
    public void createInsuranceCard(InsertInsuranceCardRequest request) throws Exception { this.insuranceCardFacade.createInsuranceCard(request); }

    public void deleteInsuranceCardById(int id) throws Exception { this.insuranceCardFacade.deleteInsuranceCardById(id); }

    public InsuranceCard getInsuranceCardById(int id) {
        return this.insuranceCardFacade.getInsuranceCardById(id);
    }
}
