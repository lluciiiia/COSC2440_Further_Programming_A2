package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

import java.util.Date;

public class InsuranceSurveyor extends Provider{

    private int insuranceManagerId;

    public InsuranceSurveyor(int id, Date createdAt, Date updatedAt, int accountId, int insuranceManagerId, String companyName, String companyAddress, String providerPhone, String providerEmail, String providerID, String providerName) {
        super(id, createdAt, updatedAt, accountId, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName);
        this.insuranceManagerId = insuranceManagerId;
    }
}
