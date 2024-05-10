package com.team2.a2.Model.InsuranceObject;

import com.team2.a2.Model.BaseEntity;

import java.util.Date;

public class ClaimDocument extends BaseEntity {
    private int claimId;

    private String imageSrc;

    public ClaimDocument(int id, Date createdAt, Date updatedAt, int claimId, String imageSrc) {
        super(id, createdAt, updatedAt);
        this.claimId = claimId;
        this.imageSrc = imageSrc;
    }
}
