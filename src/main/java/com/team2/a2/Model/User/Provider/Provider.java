package com.team2.a2.Model.User.Provider;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.Date;

public class Provider extends BaseEntity {

    private int accountId;
    private String companyName;
    private String companyAddress;
    private String phoneNumber;
    private String email;
    private String name;

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
