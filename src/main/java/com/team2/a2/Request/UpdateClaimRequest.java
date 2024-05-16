package com.team2.a2.Request;

import java.sql.Date;

public class UpdateClaimRequest {
    private int id;
    private Date claimDate;
    private Date examDate;
    private Double amount;

    // Constructor
    public UpdateClaimRequest(int id, Date claimDate, Date examDate, Double amount) {
        this.id = id;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.amount = amount;
    }

    // Getters
    public int getId() {
        return id;
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
