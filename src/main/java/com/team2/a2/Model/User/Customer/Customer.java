package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.InsuranceObject.Bank;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;

import java.util.ArrayList;
import java.util.List;

abstract public class Customer extends Account {
    protected String name;
    protected String homeAddress;
    protected String phoneNumber;
    protected String email;
    protected List<Claim> claims;
    protected InsuranceCard insuranceCard;
    protected Bank bank;

    //initializer
    public Customer (int id, String username, String password, String name, String homeAddress,
                     String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank, AccountType type) {
        super(id, username, password, type);
        this.name = name;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.claims = claims;
        this.insuranceCard = insuranceCard;
        this.bank = bank;
    }

    //getter function

    public String getName() {
        return name;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public Bank getBank() {
        return bank;
    }

    //setter function
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
