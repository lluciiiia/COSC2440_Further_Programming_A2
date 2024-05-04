package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

public class Bank extends BaseEntity {
    private String name;
    private String accountNumber;

    public Bank(int id, String name, String accountNumber) {
        super(id);
        this.name = name;
        this.accountNumber = accountNumber;
    }

    //getter function
    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
