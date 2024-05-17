package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.FacadeImpl.ClaimDocumentFacadeImpl;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;

public class ClaimDocumentController {

    ClaimDocumentFacade claimDocumentFacade;

    public ClaimDocumentController() {
        this.claimDocumentFacade = new ClaimDocumentFacadeImpl();
    }

    public void createClaimDocument(InsertClaimDocumentRequest request) throws Exception { claimDocumentFacade.createClaimDocument(request);}

    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) throws Exception { return claimDocumentFacade.getClaimDocumentsByClaimId(claimId);}

    public void updateClaimDocument(UpdateClaimDocumentRequest request) throws Exception { claimDocumentFacade.updateClaimDocument(request); }

    public ClaimDocument getClaimDocumentById(int id) { return claimDocumentFacade.getClaimDocumentById(id); }

    public void deleteClaimDocumentById(int id) throws Exception { claimDocumentFacade.deleteClaimDocumentById(id); }

    public void addClaimDocument(InsertClaimDocumentRequest request) throws Exception { claimDocumentFacade.addClaimDocument(request); }
}
