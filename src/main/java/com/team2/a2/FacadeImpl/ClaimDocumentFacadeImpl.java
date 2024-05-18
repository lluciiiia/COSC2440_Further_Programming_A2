package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
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
    public void createClaimDocument(InsertClaimDocumentRequest request) throws Exception {
        Claim claim = claimRepository.getClaimById(request.getClaimId());
        if (claim == null) throw new Exception("Claim doesn't exist");

        claimDocumentRepository.createClaimDocument(request);
    }

    @Override
    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception {
        Claim claim = claimRepository.getClaimById(claimId);
        if (claim == null) throw new Exception("Claim doesn't exist");

        return claimDocumentRepository.getClaimDocumentsByClaimId(claimId);
    }

    @Override
    public void updateClaimDocument(UpdateClaimDocumentRequest request) throws Exception {
        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(request.getId());
        if (claimDocument == null) throw new Exception("Claim document doesn't exist");

        claimDocumentRepository.updateClaimDocument(request);
    }

    @Override
    public ClaimDocument getClaimDocumentById(int id) {
        return claimDocumentRepository.getClaimDocumentById(id);
    }

    @Override
    public void deleteClaimDocumentById(int id) throws Exception {
        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(id);
        if (claimDocument == null) throw new Exception("Claim document doesn't exist");

        claimDocumentRepository.deleteClaimDocumentById(id);
    }

    @Override
    public void addClaimDocument(InsertClaimDocumentRequest request) throws Exception {
        Claim claim = claimRepository.getClaimById(request.getClaimId());
        if (claim == null) throw new Exception("Claim doesn't exist");

        claimDocumentRepository.createClaimDocument(request);

        if (claim.getDocumentRequested() == true) claimRepository.updateClaimDocumentRequested(claim.getId(), false);
    }
}
