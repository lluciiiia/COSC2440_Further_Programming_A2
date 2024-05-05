package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyOwner extends BaseEntity {
    private int accountId;
    private String name;
    private List<Customer> beneficiaries;

    public PolicyOwner(int id, Date createdAt, Date updatedAt, int accountId, String name,List<Customer> beneficiaries) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.name = name;
        this.beneficiaries = beneficiaries;
    }
}
