package com.team2.a2.Controller;

import com.team2.a2.Facade.CustomerFacade;
import com.team2.a2.Facade.PolicyOwnerFacade;
import com.team2.a2.FacadeImpl.PolicyOwnerFacadeImpl;
import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import java.util.List;

public class PolicyOwnerController {

    private PolicyOwnerFacade policyOwnerFacade;
    public PolicyOwnerController() {
        this.policyOwnerFacade = new PolicyOwnerFacadeImpl();
    }

    public PolicyOwner getPolicyOwnerByAccountId(int id) {
        return policyOwnerFacade.getPolicyOwnerByAccountId(id);
    }

    public List<PolicyOwner> getAllPolicyOwners() { return policyOwnerFacade.getAllPolicyOwners(); }

    public void createPolicyOwner(InsertPolicyOwnerRequest request) { policyOwnerFacade.createPolicyOwner(request); }

    public void deletePolicyOwnerById(int id) { policyOwnerFacade.deletePolicyOwnerById(id); }

    public PolicyOwner getPolicyOwnerById(int id) { return policyOwnerFacade.getPolicyOwnerById(id);
    }

    public void updatePolicyOwner(UpdatePolicyOwnerRequest request) { policyOwnerFacade.updatePolicyOwner(request); }
}
