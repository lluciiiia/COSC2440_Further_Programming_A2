package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.InsuranceCard;

public interface InsuranceCardFacade {

    public InsuranceCard getInsuranceCardByCustomerID(int customerId);

    void deleteInsuranceCardById(int id) throws Exception;

    InsuranceCard getInsuranceCardById(int id);
}
