package com.team2.a2.Facade;

import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Request.InsertInsuranceManagerRequest;
import com.team2.a2.Request.UpdateProviderRequest;

import java.util.List;

public interface InsuranceManagerFacade {

    public InsuranceManager getInsuranceManagerByAccountId(int accountId);

    List<InsuranceManager> getAllInsuranceManagers();

    void deleteInsuranceManagerById(int id, int userAccountId);

    void createInsuranceManager(InsertInsuranceManagerRequest request, int userAccountId) throws Exception;

    InsuranceManager getInsuranceManagerById(int id);

    void updateInsuranceManager(UpdateProviderRequest request, int userAccountId) throws Exception;
}
