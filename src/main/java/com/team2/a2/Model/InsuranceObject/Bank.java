package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class Bank extends BaseEntity {

    private int customerId;
    private String name;
    private String accountNumber;

    public Bank(int id, Date createdAt, Date updatedAt, int customerId, String name, String accountNumber) {
        super(id, createdAt, updatedAt);
        this.customerId = customerId;
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
