package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

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

    @Override
    public void updateClaimDocument(UpdateClaimDocumentRequest request) {
        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(request.getId());
        if (claimDocument == null) return;

        claimDocumentRepository.updateClaimDocument(request);
    }

    @Override
    public ClaimDocument getClaimDocumentById(int id) {
        return claimDocumentRepository.getClaimDocumentById(id);
    }

    @Override
    public void deleteClaimDocumentById(int id) {
        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(id);
        if (claimDocument == null) return;

        claimDocumentRepository.deleteClaimDocumentById(id);
    }
}
