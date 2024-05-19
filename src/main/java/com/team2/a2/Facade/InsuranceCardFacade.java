package com.team2.a2.Facade;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.InsuranceObject.InsuranceCard;

public interface InsuranceCardFacade {

    public InsuranceCard getInsuranceCardByCustomerID(int customerId);

    void deleteInsuranceCardById(int id, int userAccountId) throws Exception;

    InsuranceCard getInsuranceCardById(int id);
}
