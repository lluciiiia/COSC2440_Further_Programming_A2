package com.team2.a2.Model.User.Customer;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyOwner extends BaseEntity {
    private int accountId;
    private String name;

    public PolicyOwner(int id, Date createdAt, Date updatedAt, int accountId, String name) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.name = name;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }
}
