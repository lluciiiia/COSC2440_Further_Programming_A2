package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.List;

public interface CustomerFacade {
    Customer getCustomerById(int id);

    List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId);

    List<Customer> getAllPolicyHolders();

    List<Customer> getAllPolicyHoldersByPolicyOwnerId(int policyOwnerId);

    public Customer getCustomerByAccountId(int accountID);

    List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) throws Exception;

    Customer createCustomer(InsertCustomerRequest request, int userAccountId) throws Exception;

    List<Customer> getAllCustomers();

    Customer updateCustomer(UpdateCustomerRequest request, int userAccountId) throws Exception;


    void deleteCustomerById(int id, int userAccountId) throws Exception;
}
