package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.FacadeImpl.InsuranceCardFacadeImpl;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;

public class InsuranceCardController {

    private InsuranceCardFacade insuranceCardFacade;

    public InsuranceCardController() {
        this.insuranceCardFacade = new InsuranceCardFacadeImpl();
    }

    public InsuranceCard getInsuranceCardByCustomerID(int customerID) {
        return insuranceCardFacade.getInsuranceCardByCustomerID(customerID);
    }

    public void deleteInsuranceCardById(int id, int userAccountId) throws Exception { this.insuranceCardFacade.deleteInsuranceCardById(id, userAccountId); }

    public InsuranceCard getInsuranceCardById(int id) {
        return this.insuranceCardFacade.getInsuranceCardById(id);
    }
}
