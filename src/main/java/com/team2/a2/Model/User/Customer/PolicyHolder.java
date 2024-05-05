package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Bank;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyHolder extends Customer {

    public PolicyHolder(int id, Date createdAt, Date updatedAt, int accountId, int policyOwnerId, String name, String homeAddress, String phoneNumber, String email) {
        super(id, createdAt, updatedAt, accountId, policyOwnerId, name, homeAddress, phoneNumber, email);
    }
}
