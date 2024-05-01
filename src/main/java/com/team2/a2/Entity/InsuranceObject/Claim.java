package com.team2.a2.Entity.InsuranceObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Claim {
    private String claimID;
    private Date claimDate;
    private Date examDate;
    private List<String> documents;
    private Double claimAmount;
    private Status status;

    //default constructor
    public Claim() {
        this.claimID = "default";
        this.claimDate = new Date();
        this.examDate = new Date();
        this.documents = new ArrayList<>();
        this.claimAmount = 0.0;
        this.status = Status.New;
    }

    //initializer
    public Claim(String claimID, Date claimDate, Date examDate,
                 Double claimAmount, List<String> documents, Status status) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
    }

    //getter function
    public String getClaimID() {
        return claimID;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public Status getStatus() {
        return status;
    }

    //setter
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
}
