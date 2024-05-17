package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.ClaimFacade;
import com.team2.a2.Model.InsuranceObject.Claim;
import com.team2.a2.Model.Enum.ClaimStatus;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Repository.ClaimDocumentRepository;
import com.team2.a2.Repository.ClaimRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Request.InsertClaimRequest;
import com.team2.a2.Request.UpdateClaimRequest;

import java.util.List;

public class ClaimFacadeImpl implements ClaimFacade {

    ClaimRepository claimRepository;
    CustomerRepository customerRepository;
    ClaimDocumentRepository claimDocumentRepository;

    public ClaimFacadeImpl() {
        this.claimRepository = new ClaimRepository();
        this.customerRepository = new CustomerRepository();
        this.claimDocumentRepository = new ClaimDocumentRepository();
    }

    @Override
    public Claim getClaimById(int id) {
     return claimRepository.getClaimById(id);
    }

    @Override
    public List<Claim> getClaimsByCustomerId(int customerId) { return claimRepository.getClaimsByCustomerId(customerId); }

    @Override
    public void deleteClaimById(int id) {
        List<ClaimDocument> claimDocuments = claimDocumentRepository.getClaimDocumentsByClaimId(id);

        for (ClaimDocument claimDocument : claimDocuments) {
            claimDocumentRepository.deleteClaimDocumentById(claimDocument.getId());
        }

        claimRepository.deleteClaimById(id);
    }

    @Override
    public void updateClaimStatus(int id, ClaimStatus status) throws Exception {
        Claim claim = claimRepository.getClaimById(id);
        if (claim == null) throw new Exception("Claim doesn't exist");

        switch (status) {
            case ACCEPTED:
                if (claim.getStatus() == ClaimStatus.REJECTED)
                    throw new Exception("Rejected claim can't be accepted");
                break;
            case REJECTED:
                if (claim.getStatus() == ClaimStatus.ACCEPTED)
                    throw new Exception("Accepted claim can't be rejected");
                break;
            case PROCESSING:
                if (claim.getStatus() != ClaimStatus.NEW)
                    throw new Exception("Only new claims can be processed");
                break;
        }

        claimRepository.updateClaimStatus(id, status);
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.getAllClaims();
    }

    @Override
    public void createClaim(InsertClaimRequest request) throws Exception {
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        if (customer == null) throw new Exception("Customer doesn't exist");

        claimRepository.createClaim(request);
    }

    @Override
    public void updateClaimDocumentRequested(int id, boolean isRequested) throws Exception {

        Claim claim = claimRepository.getClaimById(id);
        if (claim == null) return;

        if (isRequested) {
            if (claim.getDocumentRequested() == true)
                throw new Exception("The document request status remains the same");
        } else {
            if (claim.getDocumentRequested() == false)
                throw new Exception("The document request status remains the same");
            ;
        }

        claimRepository.updateClaimDocumentRequested(id, isRequested);
    }

    @Override
    public void updateClaim(UpdateClaimRequest request) throws Exception {
        Claim claim = claimRepository.getClaimById(request.getId());
        if (claim == null) throw new Exception("Claim doesn't exist");

        claimRepository.updateClaim(request);
    }

    @Override
    public Double getAcceptedClaimsTotalAmount() {
        return claimRepository.getAcceptedClaimsTotalAmount();
    }
}
