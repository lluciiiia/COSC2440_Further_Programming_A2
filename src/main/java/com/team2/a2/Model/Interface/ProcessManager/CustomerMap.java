package com.team2.a2.Model.Interface.ProcessManager;

import com.team2.a2.Model.User.Customer.Customer;

import java.util.List;

public interface CustomerMap {
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    Customer getOne(String customerID);
    List<Customer> getAll();
}
