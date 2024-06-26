package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.DependentFacade;
import com.team2.a2.FacadeImpl.DependentFacadeImpl;
import com.team2.a2.Model.User.Customer.Dependent;

public class DependentController {

    private DependentFacade dependentFacade;

    public DependentController() {
        this.dependentFacade = new DependentFacadeImpl();
    }

    public Dependent getDependentByCustomerId(int id) {
        return dependentFacade.getDependentByCustomerId(id);
    }

    public void deleteDependentById(int id, int userAccountId) throws Exception { dependentFacade.deleteDependentById(id, userAccountId); }

    public Dependent getDependentById(int id) { return dependentFacade.getDependentById(id);
    }
}
