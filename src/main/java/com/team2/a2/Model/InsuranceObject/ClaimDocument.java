package com.team2.a2.Model.InsuranceObject;

/**
 * @author <Team 2>
 */

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

    // Getter for claimId
    public int getClaimId() {
        return claimId;
    }

    // Setter for claimId
    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    // Getter for imageSrc
    public String getImageSrc() {
        return imageSrc;
    }

    // Setter for imageSrc
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
