package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.FacadeImpl.ClaimDocumentFacadeImpl;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ClaimDocumentController {

    ClaimDocumentFacade claimDocumentFacade;

    public ClaimDocumentController() {
        this.claimDocumentFacade = new ClaimDocumentFacadeImpl();
    }

    public List<String> getImageSourcesByClaimId(int claimId) throws Exception {
        List<ClaimDocument> documents = claimDocumentFacade.getClaimDocumentsByClaimId(claimId);
        return documents.stream()
                .map(ClaimDocument::getImageSrc)
                .collect(Collectors.toList());
    }

    public void createClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception { claimDocumentFacade.createClaimDocument(request, userAccountId);}

    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception { return claimDocumentFacade.getClaimDocumentsByClaimId(claimId);}

    public void updateClaimDocument(UpdateClaimDocumentRequest request, int userAccountId) throws Exception { claimDocumentFacade.updateClaimDocument(request, userAccountId); }

    public ClaimDocument getClaimDocumentById(int id) { return claimDocumentFacade.getClaimDocumentById(id); }

    public void deleteClaimDocumentById(int id, int userAccountId) throws Exception { claimDocumentFacade.deleteClaimDocumentById(id, userAccountId); }

    public void addClaimDocument(InsertClaimDocumentRequest request, int userAccountId) throws Exception { claimDocumentFacade.addClaimDocument(request, userAccountId); }
}
