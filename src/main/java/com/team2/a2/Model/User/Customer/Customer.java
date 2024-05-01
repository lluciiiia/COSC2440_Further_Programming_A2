package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.InsuranceObject.Bank;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.User;

import java.util.ArrayList;
import java.util.List;

abstract public class Customer extends User {
    protected String customerID;
    protected String customerName;
    protected String homeAddress;
    protected String phoneNumber;
    protected String email;
    protected List<Claim> claims;
    protected InsuranceCard insuranceCard;
    protected Bank bank;

    //default constructor
    public Customer(){
        super();
        this.customerID = "default";
        this.customerName = "default";
        this.homeAddress = "default";
        this.phoneNumber = "default";
        this.email = "default";
        this.claims = new ArrayList<>();
        this.insuranceCard = new InsuranceCard();
        this.bank = new Bank();
    }

    //initializer
    public Customer (String username, String password, String customerID, String customerName, String homeAddress,
                     String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank) {
        super(username, password);
        this.customerID = customerID;
        this.customerName = customerName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.claims = claims;
        this.insuranceCard = insuranceCard;
        this.bank = bank;
    }

    //getter function
    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
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
