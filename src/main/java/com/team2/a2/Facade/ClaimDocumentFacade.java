package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;

import java.util.List;

public interface ClaimDocumentFacade {
    void createClaimDocument(InsertClaimDocumentRequest request);

    List<ClaimDocument> getClaimDocumentsByClaimId(int claimId);
}
