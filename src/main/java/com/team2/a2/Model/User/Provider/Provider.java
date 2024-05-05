package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.Date;

public class Provider extends BaseEntity {

    private int accountId;
    protected String companyName;
    protected String companyAddress;
    protected String providerPhone;
    protected String providerEmail;
    protected String providerID;
    protected String providerName;

    //initialize
    public Provider(int id, Date createdAt, Date updatedAt, int accountId, String companyName, String companyAddress,
                    String providerPhone, String providerEmail, String providerID, String providerName) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.providerPhone = providerPhone;
        this.providerEmail = providerEmail;
        this.providerID = providerID;
        this.providerName = providerName;
    }

    //getter function
    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getproviderPhone() {
        return providerPhone;
    }

    public String getproviderEmail() {
        return providerEmail;
    }

    public String getproviderID() {
        return providerID;
    }

    public String getproviderName() {
        return providerName;
    }

    //setter function
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void setCompanyPhone(String providerPhone) {
        this.providerPhone = providerPhone;
    }

    public void setCompanyEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }
}
