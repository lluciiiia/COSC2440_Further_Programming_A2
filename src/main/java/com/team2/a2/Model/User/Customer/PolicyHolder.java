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


    private List<Dependent> dependents;


    public PolicyHolder(int id, Date createdAt, Date updatedAt, int accountId, String name, String homeAddress, String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank, AccountType type, List<Dependent> dependents) {
        super(id, createdAt, updatedAt, accountId, name, homeAddress, phoneNumber, email, claims, insuranceCard, bank, type);
        this.dependents = dependents;
    }
}
