package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;

import java.util.List;

public interface ClaimFacade {
    Claim getClaimById(int id);

    List<Claim> getClaimsByCustomerId(int id);

    void deleteClaimById(int id);

    void updateClaimStatus(int id, ClaimStatus status);
    List<Claim> getAllClaims();

    void createClaim(InsertClaimRequest request);

    void updateClaimDocumentRequested(int id, boolean isRequested);

    void updateClaim(UpdateClaimRequest request);

    Double getAcceptedClaimsTotalAmount();
}
