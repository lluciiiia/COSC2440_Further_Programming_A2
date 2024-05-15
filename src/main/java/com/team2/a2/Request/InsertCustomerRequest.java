package com.team2.a2.Request;

import com.team2.a2.Model.Enum.CustomerType;

public class InsertCustomerRequest {

    private String username;
    private String password;
    private int policyOwnerAccountId;
    private int policyHolderId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private CustomerType type;

    // Constructor
    public InsertCustomerRequest(String username, String password, int policyOwnerAccountId, int policyHolderId, String name,
                                 String address, String phoneNumber, String email, CustomerType type) {
        this.username = username;
        this.password = password;
        this.policyOwnerAccountId = policyOwnerAccountId;
        this.policyHolderId = policyHolderId;
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

    public int getPolicyHolderId() {
        return policyHolderId;
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
