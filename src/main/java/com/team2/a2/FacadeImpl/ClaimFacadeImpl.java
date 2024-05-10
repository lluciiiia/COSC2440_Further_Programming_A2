package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Repository.ClaimRepository;

public class ClaimFacadeImpl implements ClaimFacade {

    ClaimRepository claimRepository;

    public ClaimFacadeImpl() {
        this.claimRepository = new ClaimRepository();
    }

    @Override
    public Claim getClaimById(int id) {
     return claimRepository.getClaimById(id);
    }
}
