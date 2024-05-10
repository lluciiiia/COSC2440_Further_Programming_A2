package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.FacadeImpl.ClaimFacadeImpl;
import com.team2.a2.Model.InsuranceObject.Claim;

import java.util.List;

public class ClaimController {

    private ClaimFacade claimFacade;

    public ClaimController() {
        this.claimFacade = new ClaimFacadeImpl();
    }

    public Claim getClaimById(int id) { return claimFacade.getClaimById(id); }

    public List<Claim> getClaimsByCustomerId(int customerId) { return claimFacade.getClaimsByCustomerId(customerId); }

}
