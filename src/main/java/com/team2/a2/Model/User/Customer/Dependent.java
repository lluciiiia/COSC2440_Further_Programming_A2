package com.team2.a2.Model.User.Customer;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class Dependent extends BaseEntity {

    private int customerId;
    private int policyHolderId;


    public Dependent(int id, Date createdAt, Date updatedAt, int customerId, int policyHolderId) {
        super(id, createdAt, updatedAt);
        this.customerId = customerId;
        this.policyHolderId = policyHolderId;
    }

    public int getCustomerId() { return customerId; }
    public int getPolicyHolderId() { return policyHolderId; }
}
