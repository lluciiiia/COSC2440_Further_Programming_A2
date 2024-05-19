package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

public class InsertClaimDocumentRequest {
    private int claimId;
    private String imageSrc;

    // Constructor
    public InsertClaimDocumentRequest(int claimId, String imageSrc) {
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
