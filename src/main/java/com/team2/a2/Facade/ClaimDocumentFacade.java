package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;

public interface ClaimDocumentFacade {
    void createClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception;

    List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception;

    void updateClaimDocument(UpdateClaimDocumentRequest request, int userAccountId) throws Exception;

    ClaimDocument getClaimDocumentById(int id);

    void deleteClaimDocumentById(int id, int userAccountId) throws Exception;

    void addClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception;
}
