package com.team2.a2.Model.User.Customer;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.CustomerType;

import java.util.Date;

public class Customer extends BaseEntity {

    private int accountId;
    private int policyOwnerId; // As a beneficiary
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    private CustomerType type;

    public Customer (int id, Date createdAt, Date updatedAt, int accountId, int policyOwnerId, String name, String address,
                     String phoneNumber, String email, CustomerType type) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.policyOwnerId = policyOwnerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public int getAccountId() {
        return accountId;
    }
    public CustomerType getType() {
        return this.type;
    }

    public int getPolicyOwnerId() {
        return policyOwnerId;
    }

    public String getAddress() {
        return address;
    }
}
