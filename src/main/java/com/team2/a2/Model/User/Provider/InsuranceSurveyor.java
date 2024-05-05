package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

import java.util.Date;

public class InsuranceSurveyor extends Provider{

    private int insuranceManagerId;

    public InsuranceSurveyor(int id, Date createdAt, Date updatedAt, int accountId, int insuranceManagerId, String companyName, String companyAddress, String phoneNumber, String email, String name) {
        super(id, createdAt, updatedAt, accountId, companyName, companyAddress, phoneNumber, email, name);
        this.insuranceManagerId = insuranceManagerId;
    }
}
