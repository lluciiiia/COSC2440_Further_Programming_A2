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
    private ClaimStatus status;


}
