package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class InsuranceCard extends BaseEntity {

    private int customerId;
    private String cardNumber;
    private Date expiryDate;
    private String bankName;
    private String accountNumber;

    public InsuranceCard(int id, Date createdAt, Date updatedAt, int customerId, String cardNumber, Date expiryDate, String bankName, String accountNumber) {
        super(id, createdAt, updatedAt);
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() { return customerId; }

}
