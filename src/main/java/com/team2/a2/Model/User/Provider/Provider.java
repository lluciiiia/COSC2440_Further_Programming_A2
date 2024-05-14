package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.Date;

public class Provider extends BaseEntity {

    private int accountId;
    protected String companyName;
    protected String companyAddress;
    protected String phoneNumber;
    protected String email;
    protected String name;

    public Provider(int id, Date createdAt, Date updatedAt, int accountId, String companyName, String companyAddress,
                    String phoneNumber, String email, String name) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
