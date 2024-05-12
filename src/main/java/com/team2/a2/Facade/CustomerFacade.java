package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.Customer;

import java.util.List;

public interface CustomerFacade {
    List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerId);
    public Customer getCustomerById(int accountID);
}
