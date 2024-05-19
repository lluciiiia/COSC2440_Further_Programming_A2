package com.team2.a2.FacadeImpl;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.DependentFacade;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Repository.*;

public class DependentFacadeImpl implements DependentFacade {
    DependentRepository dependentRepository;
    LogRepository logRepository;
    AccountRepository accountRepository;

    public DependentFacadeImpl() {

        this.dependentRepository = new DependentRepository();
        this.logRepository = new LogRepository();
        this.accountRepository = new AccountRepository();
    }

    @Override
    public Dependent getDependentByCustomerId(int customerID) {
        return dependentRepository.getDependentByCustomerId(customerID);
    }

    @Override
    public void deleteDependentById(int id, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        Dependent dependent = dependentRepository.getDependentById(id);
        if (dependent == null) throw new Exception("Dependent doesn't exist");

        dependentRepository.deleteDependentById(id);

        logRepository.createLog(userAccountId, "Deleted a dependent with id " + id);
    }

    @Override
    public Dependent getDependentById(int id) {
        return dependentRepository.getDependentById(id);
    }

}
