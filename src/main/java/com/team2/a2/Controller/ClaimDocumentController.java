package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.FacadeImpl.ClaimDocumentFacadeImpl;
import com.team2.a2.FacadeImpl.ClaimFacadeImpl;
import com.team2.a2.Model.InsuranceObject.ClaimDocument;
import com.team2.a2.Request.InsertClaimDocumentRequest;
import com.team2.a2.Request.UpdateClaimDocumentRequest;

import java.util.List;

public class ClaimDocumentController {

    ClaimDocumentFacade claimDocumentFacade;

    public ClaimDocumentController() {
        this.claimDocumentFacade = new ClaimDocumentFacadeImpl();
    }

    public void createClaimDocument(InsertClaimDocumentRequest request) { claimDocumentFacade.createClaimDocument(request);}

    public List<ClaimDocument> getClaimDocumentsByClaimId(int claimId) { return claimDocumentFacade.getClaimDocumentsByClaimId(claimId);}

    public void updateClaimDocument(UpdateClaimDocumentRequest request) { claimDocumentFacade.updateClaimDocument(request); }

    public ClaimDocument getClaimDocumentById(int id) { return claimDocumentFacade.getClaimDocumentById(id); }
}
