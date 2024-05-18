package org.example;

import com.team2.a2.Controller.ClaimController;
import com.team2.a2.Controller.InsuranceCardController;
import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.CustomerController;
import com.team2.a2.Model.User.Customer.Dependent;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.DependentRepository;
import com.team2.a2.Request.InsertCustomerRequest;
import com.team2.a2.Request.UpdateCustomerRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {

    private CustomerController customerController;
    private InsuranceCardController insuranceCardController;
    private ClaimController claimController;
    private CustomerRepository customerRepository;
    private DependentRepository dependentRepository;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        customerController = new CustomerController();
        insuranceCardController = new InsuranceCardController();
        claimController = new ClaimController();
        customerRepository = new CustomerRepository();
        dependentRepository = new DependentRepository();
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
        InsertCustomerRequest policyHolderRequest = new InsertCustomerRequest("new ph username", "12345", 4, "wanna be a ph",
                "hochiminh", "098765", "123456@gmail.com", CustomerType.POLICY_HOLDER,
                "1234567890123456",
                Date.valueOf("2024-12-31"),
                "VPBank",
                "12390");

        InsertCustomerRequest dependentRequest = new InsertCustomerRequest("new dep username", "12345", 4, 12, "wanna be a dp", "danang", "098765", "12ewca6@gmail.com", CustomerType.DEPENDENT,
                "1234567890123456",
                Date.valueOf("2024-12-31"),
                "VPBank",
                "12390");

        customerController.createCustomer(policyHolderRequest);
        customerController.createCustomer(dependentRequest);
    }

    @Test
    public void testUpdateCustomer() {

        int accountId = 48;

        Customer previousCustomer = customerController.getCustomerByAccountId(accountId);

        assertNotNull(previousCustomer, "Previous customer should NOT be null.");
        assertEquals(CustomerType.DEPENDENT, previousCustomer.getType(), "Previous customer's type should be DEPENDENT.");

        UpdateCustomerRequest request = new UpdateCustomerRequest(previousCustomer.getId(), "already dp", "Hoi an", "12345678987654", "changed@gmail.com");

        customerController.updateCustomer(request);

        Customer updatedCustomer = customerController.getCustomerByAccountId(accountId);

        assertEquals(previousCustomer.getId(), updatedCustomer.getId(), "The id should be the same as before.");
        assertEquals(previousCustomer.getType(), updatedCustomer.getType(), "The type should be the same as before.");
        assertNotEquals(previousCustomer.getName(), updatedCustomer.getName(), "The name should be NOT the same as before.");
        assertNotEquals(previousCustomer.getAddress(), updatedCustomer.getAddress(), "The address should be NOT the same as before.");
        assertNotEquals(previousCustomer.getPhoneNumber(), updatedCustomer.getPhoneNumber(), "The phone number should be NOT the same as before.");
        assertNotEquals(previousCustomer.getEmail(), updatedCustomer.getEmail(), "The email should be NOT the same as before.");
    }


    @Test
    public void testGetAllPolicyHoldersByPolicyOwnerId() {
        int policyOwnerId = 1;
        List<Customer> customers = customerController.getAllPolicyHoldersByPolicyOwnerId(policyOwnerId);
        assertNotNull(customers, "Customers should NOT be null.");
        assertEquals(8, customers.size(), "Customers size should be 6.");
    }

    @Test
    public void testDeleteCustomerById() {
        int accountId = 3;
        List<Dependent> dependents = new ArrayList<>();

        Customer customer = customerController.getCustomerByAccountId(accountId);

        if (customer.getType() == CustomerType.POLICY_HOLDER) {
            dependents = dependentRepository.getDependentsByPolicyHolderId(customer.getId());
        }

        assertNotNull(customer, "Customer should NOT be null for testing.");

        customerController.deleteCustomerById(customer.getId());

        assertNull(customerRepository.getCustomerById(customer.getId()), "Customer should be deleted");

        if (customer.getType() == CustomerType.POLICY_HOLDER) {
            for (Dependent dependent : dependents) {
                assertNull(dependentRepository.getDependentById(dependent.getId()), "Dependent " + dependent.getId() + " should be deleted");
            }
        }

        assertNull(insuranceCardController.getInsuranceCardByCustomerID(customer.getId()), "Insurance card should be deleted");

        List<Claim> claims = claimController.getClaimsByCustomerId(customer.getId());
        assertTrue(claims.isEmpty(), "All claims should be deleted");
    }
}
