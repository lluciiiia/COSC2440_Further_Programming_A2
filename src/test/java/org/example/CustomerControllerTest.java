package org.example;

import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Request.InsertCustomerRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {

    private CustomerController customerController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        customerController = new CustomerController();
    }

    @Test
    public void testGetCustomerByAccountId() {
        int accountId = 3;

        Customer expectedCustomer = new Customer(12, Date.valueOf("2024-05-12"), Date.valueOf("2024-05-12"),
                accountId,  1, "ph", "123 nguyen van linh",
                "123090", "ccc@gmail.com", CustomerType.POLICY_HOLDER);

        Customer customer = customerController.getCustomerByAccountId(accountId);

        assertEquals(customer.getId(), expectedCustomer.getId());
        assertEquals(customer.getPolicyOwnerId(), expectedCustomer.getPolicyOwnerId());
        assertEquals(customer.getEmail(), expectedCustomer.getEmail());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerController.getAllCustomers();

        assertNotNull(customers, "Customers should NOT be null.");
        assertEquals( 9, customers.size(), "Customers size should be 9.");
    }

    @Test
    public void testGetCustomersByPolicyOwnerId() {
        int policyOwnerId = 1;

        List<Customer> customers = customerController.getCustomersByPolicyOwnerId(policyOwnerId);

        assertNotNull(customers, "Customers should NOT be null.");
        assertEquals(6, customers.get(0).getId(), "The first customer's id should be 6.");
    }

    @Test
    public void testGetDependentsByPolicyHolderAccountId() {
        int policyHolderAccountId = 3;

        List<Customer> dependents = customerController.getDependentsByPolicyHolderAccountId(policyHolderAccountId);

        assertEquals( 4, dependents.size(), "Dependents size should be 4.");
    }

    @Test
    public void testCreateCustomer() {
        InsertCustomerRequest policyHolderRequest = new InsertCustomerRequest("new ph username", "12345", 4, "wanna be a ph", "hochiminh", "098765", "123456@gmail.com", CustomerType.POLICY_HOLDER);

        InsertCustomerRequest dependentRequest = new InsertCustomerRequest("new dep username", "12345", 4, 12, "wanna be a dp", "danang", "098765", "12ewca6@gmail.com", CustomerType.DEPENDENT);

        customerController.createCustomer(policyHolderRequest);
        customerController.createCustomer(dependentRequest);
    }

}
