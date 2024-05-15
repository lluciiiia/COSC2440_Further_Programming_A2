package com.team2.a2.Request;

import java.sql.Date;

public class InsertInsuranceCardRequest {

    private int customerId;
    private String cardNumber;
    private Date expiryDate;

    private String bankName;
    private String accountNumber;

    public InsertInsuranceCardRequest(int customerId, String cardNumber, Date expiryDate, String bankName, String accountNumber) {
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
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
}
