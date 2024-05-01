package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.InsuranceObject.Bank;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    //default constructor
    public PolicyHolder() {
        super();
        this.dependents = new ArrayList<>();
    }

    //initializer
    public PolicyHolder(String username, String password, String customerID, String customerName, String homeAddress,
                        String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank,
                        List<Dependent> dependents) {
        super(username, password, customerID, customerName, homeAddress, phoneNumber,
                email, claims, insuranceCard, bank);
        this.dependents = dependents;
    }

    //getter function
    public List<Dependent> getDependents() {
        return dependents;
    }
}
