package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimStatus;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Request.InsertClaimRequest;

import java.util.List;

public class ClaimFacadeImpl implements ClaimFacade {

    ClaimRepository claimRepository;

    public ClaimFacadeImpl() {
        this.claimRepository = new ClaimRepository();
    }

    @Override
    public Claim getClaimById(int id) {
     return claimRepository.getClaimById(id);
    }

    @Override
    public List<Claim> getClaimsByCustomerId(int customerId) { return claimRepository.getClaimsByCustomerId(customerId); }

    @Override
    public void deleteClaimById(int id) {
        claimRepository.deleteClaimById(id);
    }

    @Override
    public void updateClaimStatus(int id, ClaimStatus status) {
        claimRepository.updateClaimStatus(id, status);
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.getAllClaim();
    }
    public void createClaim(InsertClaimRequest request) {

    }
}
