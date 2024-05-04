package com.team2.a2.Model.User.Customer;

import com.team2.a2.Model.Enum.AccountType;
import com.team2.a2.Model.User.Account;

import java.util.ArrayList;
import java.util.List;

public class PolicyOwner extends Account {
    private String policyOwnerID;
    private String policyOwnerName;
    private List<Customer> beneficiaries;

    public PolicyOwner(int id, String username, String password, AccountType type) {
        super(id, username, password, type);
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
