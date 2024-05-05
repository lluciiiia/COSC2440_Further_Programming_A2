package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceManager extends Provider{

    public InsuranceManager(int id, Date createdAt, Date updatedAt, int accountId, String companyName, String companyAddress, String phoneNumber, String email, String name) {
        super(id, createdAt, updatedAt, accountId, companyName, companyAddress, phoneNumber, email, name);
    }
}
