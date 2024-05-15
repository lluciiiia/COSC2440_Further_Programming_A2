package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Request.InsertInsuranceCardRequest;

public interface InsuranceCardFacade {
    void createInsuranceCard(InsertInsuranceCardRequest request);

    public InsuranceCard getInsuranceCardByCustomerID(int customerId);
}
