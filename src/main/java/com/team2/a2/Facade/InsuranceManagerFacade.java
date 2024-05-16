package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.InsertInsuranceManagerRequest;

import java.util.List;

public interface InsuranceManagerFacade {

    public InsuranceManager getInsuranceManagerByAccountId(int accountId);

    List<InsuranceManager> getAllInsuranceManagers();

    void deleteInsuranceManagerById(int id);

    void createInsuranceManager(InsertInsuranceManagerRequest request);
}
