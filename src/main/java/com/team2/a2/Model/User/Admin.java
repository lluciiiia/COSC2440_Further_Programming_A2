package com.team2.a2.Model.User;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;

import java.util.Date;

public class Admin extends BaseEntity {
    private int accountId;
    private String name;

    public Admin(int id, Date createdAt, Date updatedAt, int accountId, String name) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.name = name;
    }

    public String getName() { return this.name; }

}


