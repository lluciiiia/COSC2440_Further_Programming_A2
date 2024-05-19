package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.FacadeImpl.ClaimFacadeImpl;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;


import java.util.List;

public class ClaimController {

    private ClaimFacade claimFacade;

    public ClaimController() {
        this.claimFacade = new ClaimFacadeImpl();
    }

    public Claim getClaimById(int id) { return claimFacade.getClaimById(id); }
    public List<Claim> getAllClaims() {
        return claimFacade.getAllClaims();
    }

    public List<Claim> getClaimsByCustomerId(int customerId) { return claimFacade.getClaimsByCustomerId(customerId); }

    public void deleteClaimById(int id, int userAccountId) throws Exception { claimFacade.deleteClaimById(id, userAccountId); }

    public void updateClaimStatus(int id, ClaimStatus status, int userAccountId) throws Exception { claimFacade.updateClaimStatus(id, status, userAccountId); }

    public void createClaim(InsertClaimRequest request, int userAccountId) throws Exception { claimFacade.createClaim(request, userAccountId); }

    public void updateClaimDocumentRequested(int id, boolean isRequested, int userAccountId) throws Exception { claimFacade.updateClaimDocumentRequested(id, isRequested, userAccountId); }

    public void updateClaim(UpdateClaimRequest request, int userAccountId) throws Exception { claimFacade.updateClaim(request, userAccountId); }

    public Double getAcceptedClaimsTotalAmount() { return claimFacade.getAcceptedClaimsTotalAmount(); }
}
