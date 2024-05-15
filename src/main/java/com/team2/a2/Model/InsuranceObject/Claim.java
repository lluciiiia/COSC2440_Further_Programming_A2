package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class Claim extends BaseEntity {

    private int customerId;

    private Date claimDate;
    private Date examDate;
    private Double amount;
    private ClaimStatus status;

    public Claim(int id, Date createdAt, Date updatedAt, int customerId, Date claimDate, Date examDate, Double amount, ClaimStatus status) {
        super(id, createdAt, updatedAt);
        this.customerId = customerId;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.amount = amount;
        this.status = status;
    }

    //getter function


    public Date getClaimDate() {
        return claimDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public Double getAmount() {
        return amount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public int getCustomerId() {
        return customerId;
    }

    //setter
    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

}
