package com.team2.a2.Controller;

import com.team2.a2.DependentView;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.FacadeImpl.AccountFacadeImpl;
import com.team2.a2.FacadeImpl.CustomerFacadeImpl;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;

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

}
