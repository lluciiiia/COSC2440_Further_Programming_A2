package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Bank;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract public class Customer extends BaseEntity {

    private int accountId;
    private String name;
    private String homeAddress;
    private String phoneNumber;
    private String email;
    private List<Claim> claims;
    private InsuranceCard insuranceCard;
    private Bank bank;


    //initializer
    public Customer (int id, Date createdAt, Date updatedAt, int accountId, String name, String homeAddress,
                     String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank, AccountType type) {
        super(id, createdAt, updatedAt);
        this.accountId = accountId;
        this.name = name;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.claims = claims;
        this.insuranceCard = insuranceCard;
        this.bank = bank;
    }

}
