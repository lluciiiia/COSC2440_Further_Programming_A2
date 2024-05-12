package com.team2.a2.Request;

import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Customer.CustomerType;

public class UpsertCustomerRequest {
    private String username;
    private String password;
    private int policyOwnerAccountId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private CustomerType type;

    // Constructor
    public UpsertCustomerRequest(String username, String password, int policyOwnerAccountId, String name,
                                 String address, String phoneNumber, String email, CustomerType type) {
        this.username = username;
        this.password = password;
        this.policyOwnerAccountId = policyOwnerAccountId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPolicyOwnerAccountId() {
        return policyOwnerAccountId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public CustomerType getType() {
        return type;
    }
}
