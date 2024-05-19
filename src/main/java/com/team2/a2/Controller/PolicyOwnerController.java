package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

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

    public void createPolicyOwner(InsertPolicyOwnerRequest request, int userAccountId) throws Exception { policyOwnerFacade.createPolicyOwner(request, userAccountId); }

    public void deletePolicyOwnerById(int id, int userAccountId) throws Exception { policyOwnerFacade.deletePolicyOwnerById(id, userAccountId); }

    public PolicyOwner getPolicyOwnerById(int id) { return policyOwnerFacade.getPolicyOwnerById(id);
    }

    public void updatePolicyOwner(UpdatePolicyOwnerRequest request, int userAccountId) throws Exception { policyOwnerFacade.updatePolicyOwner(request, userAccountId); }
}
