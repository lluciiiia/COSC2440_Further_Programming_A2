package com.team2.a2.Controller;

import com.team2.a2.DependentView;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.FacadeImpl.CustomerFacadeImpl;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.List;

public class CustomerController {

    private CustomerFacade customerFacade;

    // Constructor to initialize customerFacade
    public CustomerController() {
        this.customerFacade = new CustomerFacadeImpl();
    }
    public Customer getCustomer(int id) {
        return customerFacade.getCustomerById(id);
    }
    public List<Customer> getCustomersByPolicyOwnerAccountId(int policyOwnerAccountId) { return customerFacade.getCustomersByPolicyOwnerAccountId(policyOwnerAccountId); }

    public List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) { return customerFacade.getDependentsByPolicyHolderAccountId(policyHolderAccountId); };

    public Customer createCustomer(InsertCustomerRequest request) { return this.customerFacade.createCustomer(request); }

    public Customer updateCustomer(UpdateCustomerRequest request) { return this.customerFacade.updateCustomer(request); }

}
