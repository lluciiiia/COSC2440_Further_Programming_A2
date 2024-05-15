package com.team2.a2.Controller;

import com.team2.a2.DependentView;
import com.team2.a2.Facade.AccountFacade;
import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.FacadeImpl.CustomerFacadeImpl;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import java.util.List;

public class CustomerController {

    private CustomerFacade customerFacade;

    public CustomerController() {
        this.customerFacade = new CustomerFacadeImpl();
    }
    public Customer getCustomerByAccountId(int id) {
        return customerFacade.getCustomerByAccountId(id);
    }
    public Dependent getDependentByCustomerId(int id) {
        return customerFacade.getDependentByCustomerId(id);
    }

    public List<Customer> getAllCustomers() {
        return customerFacade.getAllCustomers();
    }

    public PolicyOwner getPolicyOwnerByAccountId(int id) {
        return customerFacade.getPolicyOwnerByAccountId(id);
    }

    public List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId) { return customerFacade.getCustomersByPolicyOwnerId(policyOwnerId); };

    public List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) { return customerFacade.getDependentsByPolicyHolderAccountId(policyHolderAccountId); };

    public Customer createCustomer(InsertCustomerRequest request) { return this.customerFacade.createCustomer(request); }

    public Customer updateCustomer(UpdateCustomerRequest request) { return this.customerFacade.updateCustomer(request); }

}
