package com.team2.a2.Model.Interface.Implement;

import com.team2.a2.Model.Interface.ProcessManager.CustomerMap;
import com.team2.a2.Model.User.Customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerManagerImp implements CustomerMap {
    private Map<String, Customer> customerMap;

    public CustomerManagerImp() {
        customerMap = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        if (!customerMap.containsKey(customer.getCustomerID())) {
            customerMap.put(customer.getCustomerID(), customer);
        } else {
            System.out.println("Customer already exists");
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        if (customerMap.containsKey(customer.getCustomerID())) {
            customerMap.remove(customer.getCustomerID());
        } else {
            System.out.println("Customer does not exist");
        }
    }

    @Override
    public Customer getOne(String customerID) {
        return customerMap.get(customerID);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customerMap.values());
    }
}
