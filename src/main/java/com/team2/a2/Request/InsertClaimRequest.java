package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.Enum.ClaimStatus;

import java.sql.Date;

public class InsertClaimRequest {

    private int customerId;
    private Date claimDate;
    private Date examDate;
    private Double amount;

    // Constructor
    public InsertClaimRequest(int customerId, Date claimDate, Date examDate, Double amount) {
        this.customerId = customerId;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public Double getAmount() {
        return amount;
    }

}
