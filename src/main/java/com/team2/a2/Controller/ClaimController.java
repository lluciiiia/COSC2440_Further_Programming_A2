package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.FacadeImpl.ClaimFacadeImpl;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimStatus;

import java.util.List;

public class ClaimController {

    private ClaimFacade claimFacade;

    public ClaimController() {
        this.claimFacade = new ClaimFacadeImpl();
    }

    public Claim getClaimById(int id) { return claimFacade.getClaimById(id); }

    public List<Claim> getClaimsByCustomerId(int customerId) { return claimFacade.getClaimsByCustomerId(customerId); }

    public void deleteClaimById(int id) { claimFacade.deleteClaimById(id); }

    public void updateClaimStatus(int id, ClaimStatus status) { claimFacade.updateClaimStatus(id, status); }

}
