package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.List;

public interface CustomerFacade {
    List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerId);

    List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId);

    List<Customer> getAllPolicyHoldersByPolicyOwnerId(int policyOwnerId);

    public Customer getCustomerByAccountId(int accountID);

    List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId);

    Customer createCustomer(InsertCustomerRequest request);

    List<Customer> getAllCustomers();

    Customer updateCustomer(UpdateCustomerRequest request);


}
