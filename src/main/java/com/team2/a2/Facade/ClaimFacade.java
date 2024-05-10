package com.team2.a2.Facade;

import com.team2.a2.Model.InsuranceObject.Claim;

import java.util.List;

public interface ClaimFacade {
    Claim getClaimById(int id);

    List<Claim> getClaimsByCustomerId(int id);
}
