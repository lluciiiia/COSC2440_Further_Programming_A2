package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;

public interface ClaimDocumentFacade {
    void createClaimDocument(InsertClaimDocumentRequest request) throws Exception;

    List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception;

    void updateClaimDocument(UpdateClaimDocumentRequest request) throws Exception;

    ClaimDocument getClaimDocumentById(int id);

    void deleteClaimDocumentById(int id) throws Exception;

    void addClaimDocument(InsertClaimDocumentRequest request) throws Exception;
}
