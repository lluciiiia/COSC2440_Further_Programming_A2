package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Request.InsertClaimDocumentRequest;

import java.util.List;

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

    @Override
    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) {
        Claim claim = claimRepository.getClaimById(claimId);
        if (claim == null) return null;

        return claimDocumentRepository.getClaimDocumentsByClaimId(claimId);
    }
}
