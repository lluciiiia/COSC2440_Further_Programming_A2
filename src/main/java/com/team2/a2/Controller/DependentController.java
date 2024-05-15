package com.team2.a2.Controller;

import com.team2.a2.Facade.DependentFacade;
import com.team2.a2.Model.User.Customer.Dependent;

public class DependentController {

    private DependentFacade dependentFacade;

    public Dependent getDependentByCustomerId(int id) {
        return dependentFacade.getDependentByCustomerId(id);
    }



}
