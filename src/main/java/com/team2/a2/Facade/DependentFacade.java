package com.team2.a2.Facade;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.User.Customer.Dependent;

public interface DependentFacade {
    Dependent getDependentByCustomerId(int id);

    void deleteDependentById(int id, int userAccountId) throws Exception;

    Dependent getDependentById(int id);
}
