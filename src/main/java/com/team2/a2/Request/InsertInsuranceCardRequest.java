package com.team2.a2.Request;

import java.sql.Date;

public class InsertInsuranceCardRequest {

    private int customerId;
    private int policyOwnerId;
    private String cardNumber;
    private Date expiryDate;

    public InsertInsuranceCardRequest(int customerId, int policyOwnerId, String cardNumber, Date expiryDate) {
        this.customerId = customerId;
        this.policyOwnerId = policyOwnerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPolicyOwnerId() {
        return policyOwnerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
