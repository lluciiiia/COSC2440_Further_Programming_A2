package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class InsuranceCard extends BaseEntity {

    private int customerId;
    private String cardNumber;
    private Date expiryDate;

    public InsuranceCard(int id, Date createdAt, Date updatedAt, int customerId, String cardNumber, Date expiryDate) {
        super(id, createdAt, updatedAt);
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    //getter function
    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

}
