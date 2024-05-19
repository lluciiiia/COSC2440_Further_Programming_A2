package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

public class UpdateClaimDocumentRequest {
    private int id;
    private String imageSrc;

    public UpdateClaimDocumentRequest(int id, String imageSrc) {
        this.id = id;
        this.imageSrc = imageSrc;
    }

    public int getId() {
        return id;
    }

    public String getImageSrc() {
        return imageSrc;
    }
}
