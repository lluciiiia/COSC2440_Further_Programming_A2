package com.team2.a2.Entity.InsuranceObject;

public class Bank {
    private String bankName;
    private String accountNumber;

    //default constructor
    public Bank() {
        this.bankName = "default";
        this.accountNumber = "default";
    }

    //initializer
    public Bank(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    //getter function
    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
