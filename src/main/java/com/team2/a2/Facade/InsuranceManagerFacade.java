package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceManager;

import java.util.List;

public interface InsuranceManagerFacade {

    public InsuranceManager getInsuranceManagerByAccountId(int accountId);

    List<InsuranceManager> getAllInsuranceManagers();
}
