package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceManagerFacade;
import com.team2.a2.Model.User.Provider.InsuranceManager;
import com.team2.a2.Repository.InsuranceManagerRepository;

import java.util.List;

public class InsuranceManagerFacadeImpl implements InsuranceManagerFacade {

    InsuranceManagerRepository insuranceManagerRepository;

    public InsuranceManagerFacadeImpl() {
        this.insuranceManagerRepository = new InsuranceManagerRepository();
    }

    @Override
    public InsuranceManager getInsuranceManagerByAccountId(int accountId) {
        return insuranceManagerRepository.getInsuranceManagerByAccountId(accountId);
    }

    @Override
    public List<InsuranceManager> getAllInsuranceManagers() {
        return insuranceManagerRepository.getAllInsuranceManagers();
    }

    @Override
    public void deleteInsuranceManagerById(int id) {
        insuranceManagerRepository.deleteInsuranceManagerById(id);
    }
}
