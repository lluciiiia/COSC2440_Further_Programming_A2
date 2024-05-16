package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Request.InsertClaimDocumentRequest;

public class ClaimDocumentFacadeImpl implements ClaimDocumentFacade {

    ClaimRepository claimRepository;
    ClaimDocumentRepository claimDocumentRepository;

    public ClaimDocumentFacadeImpl() {
        this.claimRepository = new ClaimRepository();
        this.claimDocumentRepository = new ClaimDocumentRepository();
    }

    @Override
    public void createClaimDocument(InsertClaimDocumentRequest request) {
        Claim claim = claimRepository.getClaimById(request.getClaimId());
        if (claim == null) return;

        claimDocumentRepository.createClaimDocument(request);
    }
}
