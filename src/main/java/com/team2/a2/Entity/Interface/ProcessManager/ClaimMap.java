package com.team2.a2.Entity.Interface.ProcessManager;

import com.team2.a2.Entity.InsuranceObject.Claim;

import java.util.List;

public interface ClaimMap {
    void addClaim(Claim claim);
    void removeClaim(Claim claim);
    Claim getOne(Claim claim);
    List<Claim> getAll();
}
