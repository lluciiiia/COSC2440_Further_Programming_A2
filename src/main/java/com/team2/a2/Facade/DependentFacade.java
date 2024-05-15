package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.Dependent;

public interface DependentFacade {
    Dependent getDependentByCustomerId(int id);
}
