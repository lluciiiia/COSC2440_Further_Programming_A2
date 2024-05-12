package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.List;

public interface CustomerFacade {
    List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerId);

    List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId);

    Customer createCustomer(InsertCustomerRequest request);

    Customer updateCustomer(UpdateCustomerRequest request);
}
