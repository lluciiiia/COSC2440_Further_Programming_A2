package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Request.InsertInsuranceCardRequest;

public interface InsuranceCardFacade {
    void createInsuranceCard(InsertInsuranceCardRequest request) throws Exception;

    public InsuranceCard getInsuranceCardByCustomerID(int customerId);

    void deleteInsuranceCardById(int id) throws Exception;

    InsuranceCard getInsuranceCardById(int id);
}
