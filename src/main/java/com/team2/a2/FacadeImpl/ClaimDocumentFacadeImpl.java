package com.team2.a2.FacadeImpl;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Repository.LogRepository;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ClaimDocumentFacadeImpl implements ClaimDocumentFacade {

    ClaimRepository claimRepository;
    ClaimDocumentRepository claimDocumentRepository;
    LogRepository logRepository;
    AccountRepository accountRepository;

    public ClaimDocumentFacadeImpl() {
        this.claimRepository = new ClaimRepository();
        this.claimDocumentRepository = new ClaimDocumentRepository();
        this.logRepository = new LogRepository();
        this.accountRepository = new AccountRepository();
    }

    @Override
    public void createClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        Claim claim = claimRepository.getClaimById(request.getClaimId());
        if (claim == null) throw new Exception("Claim doesn't exist");

        claimDocumentRepository.createClaimDocument(request);
        logRepository.createLog(userAccountId, "Created a claim document for a claim with id " + request.getClaimId());
    }

    @Override
    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception {
        Claim claim = claimRepository.getClaimById(claimId);
        if (claim == null) throw new Exception("Claim doesn't exist");

        return claimDocumentRepository.getClaimDocumentsByClaimId(claimId);
    }

    @Override
    public void updateClaimDocument(UpdateClaimDocumentRequest request, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(request.getId());
        if (claimDocument == null) throw new Exception("Claim document doesn't exist");

        claimDocumentRepository.updateClaimDocument(request);

        logRepository.createLog(userAccountId, "Updated a claim document with id " + request.getId());
    }

    @Override
    public ClaimDocument getClaimDocumentById(int id) {
        return claimDocumentRepository.getClaimDocumentById(id);
    }

    @Override
    public void deleteClaimDocumentById(int id, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        ClaimDocument claimDocument = claimDocumentRepository.getClaimDocumentById(id);
        if (claimDocument == null) throw new Exception("Claim document doesn't exist");

        claimDocumentRepository.deleteClaimDocumentById(id);

        logRepository.createLog(userAccountId, "Deleted a claim document with id " + id);
    }

    @Override
    public void addClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        Claim claim = claimRepository.getClaimById(request.getClaimId());
        if (claim == null) throw new Exception("Claim doesn't exist");

        claimDocumentRepository.createClaimDocument(request);

        if (claim.getDocumentRequested() == true) claimRepository.updateClaimDocumentRequested(claim.getId(), false);

        logRepository.createLog(userAccountId, "Added a claim document for a claim with id " + request.getClaimId());
    }

    @Override
    public List<String> getImageSourcesByClaimId(int claimId) throws Exception {
        Claim claim = claimRepository.getClaimById(claimId);
        if (claim == null) throw new Exception("Claim doesn't exist");

        List<ClaimDocument> documents = claimDocumentRepository.getClaimDocumentsByClaimId(claimId);

        return documents.stream()
                .map(ClaimDocument::getImageSrc)
                .collect(Collectors.toList());
    }
}
