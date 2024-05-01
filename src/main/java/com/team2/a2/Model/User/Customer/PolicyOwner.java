package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.User.User;

import java.util.ArrayList;
import java.util.List;

public class PolicyOwner extends User {
    private String policyOwnerID;
    private String policyOwnerName;
    private List<Customer> beneficiaries;

    //default constructor
    public PolicyOwner() {
        super();
        this.policyOwnerID = "default";
        this.policyOwnerName = "default";
        this.beneficiaries = new ArrayList<>();
    }

    //initializer
    public PolicyOwner(String username, String password, String policyOwnerID,
                       String policyOwnerName, List<Customer> beneficiaries) {
        super(username, password);
        this.policyOwnerID = policyOwnerID;
        this.policyOwnerName = policyOwnerName;
        this.beneficiaries = beneficiaries;
    }

    //getter function
    public String getPolicyOwnerID() {
        return policyOwnerID;
    }

    public String getPolicyOwnerName() {
        return policyOwnerName;
    }

    public List<Customer> getbeneficiaries() {
        return beneficiaries;
    }
}
