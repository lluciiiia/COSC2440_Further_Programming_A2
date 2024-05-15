package org.example;

import com.team2.a2.Model.Enum.CustomerType;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.CustomerController;
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
}
