package com.team2.a2.Facade;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;

import java.util.List;

public interface ClaimFacade {
    Claim getClaimById(int id);

    List<Claim> getClaimsByCustomerId(int id);

    void deleteClaimById(int id, int userAccountId) throws Exception;

    void updateClaimStatus(int id, ClaimStatus status, int userAccountId) throws Exception;
    List<Claim> getAllClaims();

    void createClaim(InsertClaimRequest request, int userAccountId) throws Exception;

    void updateClaimDocumentRequested(int id, boolean isRequested, int userAccountId) throws Exception;

    void updateClaim(UpdateClaimRequest request, int userAccountId) throws Exception;

    Double getAcceptedClaimsTotalAmount();
}
