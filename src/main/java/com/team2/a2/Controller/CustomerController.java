package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.FacadeImpl.CustomerFacadeImpl;
import com.team2.a2.Model.User.Customer.Customer;
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
    public Customer getCustomerById(int id) {
        return customerFacade.getCustomerById(id);
    }
    
    public List<Customer> getAllCustomers() {
        return customerFacade.getAllCustomers();
    }
    public List<Customer> getAllPolicyHolders() {
        return customerFacade.getAllPolicyHolders();
    }

    public List<Customer> getAllPolicyHoldersByPolicyOwnerId(int policyOwnerId) {
        return customerFacade.getAllPolicyHoldersByPolicyOwnerId(policyOwnerId);
    }

    public List<Customer> getCustomersByPolicyOwnerId(int policyOwnerId) { return customerFacade.getCustomersByPolicyOwnerId(policyOwnerId); };

    public List<Customer> getDependentsByPolicyHolderAccountId(int policyHolderAccountId) throws Exception { return customerFacade.getDependentsByPolicyHolderAccountId(policyHolderAccountId); };

    public Customer createCustomer(InsertCustomerRequest request, int userAccountId) throws Exception { return this.customerFacade.createCustomer(request, userAccountId); }

    public Customer updateCustomer(UpdateCustomerRequest request, int userAccountId) throws Exception { return this.customerFacade.updateCustomer(request, userAccountId); }

    public void deleteCustomerById(int id, int userAccountId) throws Exception { this.customerFacade.deleteCustomerById(id, userAccountId); }

}
