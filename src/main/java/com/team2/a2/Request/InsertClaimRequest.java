package com.team2.a2.Request;

import com.team2.a2.Model.Enum.ClaimStatus;

import java.sql.Date;

public class InsertClaimRequest {

    private int customerId;
    private String cardNumber;
    private Date expiryDate;
    private Date claimDate;
    private Date examDate;
    private Double amount;

    // Constructor
    public InsertClaimRequest(int customerId, String cardNumber, Date expiryDate, Date claimDate, Date examDate, Double amount) {
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.amount = amount;
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
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
