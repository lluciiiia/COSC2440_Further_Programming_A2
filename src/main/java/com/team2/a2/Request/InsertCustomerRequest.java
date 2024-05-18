package com.team2.a2.Request;

import com.team2.a2.Model.Enum.CustomerType;

import java.sql.Date;

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

    // Insurance Card
    private String cardNumber;
    private Date expiryDate;

    private String bankName;
    private String accountNumber;

    // Dependent Constructor
    public InsertCustomerRequest(String username, String password, int policyOwnerAccountId, int policyHolderId, String name,
                                 String address, String phoneNumber, String email, CustomerType type,
                                 String cardNumber, Date expiryDate, String bankName, String accountNumber) {
        this.username = username;
        this.password = password;
        this.policyOwnerAccountId = policyOwnerAccountId;
        this.policyHolderId = policyHolderId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    // Policy Holder Constructor
    public InsertCustomerRequest(String username, String password, int policyOwnerAccountId, String name,
                                 String address, String phoneNumber, String email, CustomerType type,
                                 String cardNumber, Date expiryDate, String bankName, String accountNumber) {
        this.username = username;
        this.password = password;
        this.policyOwnerAccountId = policyOwnerAccountId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
