package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class InsuranceCard extends BaseEntity {
    private String cardNumber;
    private Date expiryDate;

    public InsuranceCard(int id, String cardNumber, Date expiryDate) {
        super(id);
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
