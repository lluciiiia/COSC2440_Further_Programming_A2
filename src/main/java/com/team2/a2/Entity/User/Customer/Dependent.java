package com.team2.a2.Entity.User.Customer;

import com.team2.a2.Entity.InsuranceObject.Bank;
import com.team2.a2.Entity.InsuranceObject.Claim;
import com.team2.a2.Entity.InsuranceObject.InsuranceCard;

import java.util.List;

public class Dependent extends Customer {

    //default constructor
    public Dependent() {
        super();
    }

    //initializer
    public Dependent(String username, String password, String customerID, String customerName, String homeAddress,
                     String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank) {
        super(username,password, customerID, customerName, homeAddress, phoneNumber,
        email, claims, insuranceCard, bank);
    }
}
