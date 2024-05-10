package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Claim extends BaseEntity {

    private int customerId;

    private Date claimDate;
    private Date examDate;
    private Double amount;
    private Status status;

    public Claim(int id, Date createdAt, Date updatedAt, int customerId, Date claimDate, Date examDate, Double amount, Status status) {
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

    public Status getStatus() {
        return status;
    }

    //setter
    public void setStatus(Status status) {
        this.status = status;
    }

}
