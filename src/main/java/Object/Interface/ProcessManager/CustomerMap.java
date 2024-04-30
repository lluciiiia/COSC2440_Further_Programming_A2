package Object.Interface.ProcessManager;

import Object.User.Customer.Customer;

import java.util.List;

public interface CustomerMap {
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    Customer getOne(String customerID);
    List<Customer> getAll();
}
