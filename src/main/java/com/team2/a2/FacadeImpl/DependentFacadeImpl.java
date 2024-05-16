package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.DependentFacade;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.DependentRepository;
import com.team2.a2.Repository.PolicyOwnerRepository;

public class DependentFacadeImpl implements DependentFacade {
    DependentRepository dependentRepository;

    public DependentFacadeImpl() {
        this.dependentRepository = new DependentRepository();
    }

    @Override
    public Dependent getDependentByCustomerId(int customerID) {
        return dependentRepository.getDependentByCustomerId(customerID);
    }

    @Override
    public void deleteDependentById(int id) {
        Dependent dependent = dependentRepository.getDependentById(id);
        if (dependent == null) return;

        dependentRepository.deleteDependentById(id);
    }

    @Override
    public Dependent getDependentById(int id) {
        return dependentRepository.getDependentById(id);
    }

}
