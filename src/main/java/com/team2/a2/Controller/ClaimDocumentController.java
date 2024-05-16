package com.team2.a2.Controller;

import com.team2.a2.Facade.ClaimDocumentFacade;
import com.team2.a2.FacadeImpl.ClaimDocumentFacadeImpl;
import com.team2.a2.FacadeImpl.ClaimFacadeImpl;
import com.team2.a2.Request.InsertClaimDocumentRequest;

public class ClaimDocumentController {

    ClaimDocumentFacade claimDocumentFacade;

    public ClaimDocumentController() {
        this.claimDocumentFacade = new ClaimDocumentFacadeImpl();
    }

    public void createClaimDocument(InsertClaimDocumentRequest request) { claimDocumentFacade.createClaimDocument(request);}
}
