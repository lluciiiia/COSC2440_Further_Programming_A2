package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;
import com.team2.a2.Request.UpdatePolicyOwnerRequest;

import java.util.List;

public interface PolicyOwnerFacade {

    public PolicyOwner getPolicyOwnerByAccountId(int id);

    List<PolicyOwner> getAllPolicyOwners();

    void createPolicyOwner(InsertPolicyOwnerRequest request, int userAccountId) throws Exception;

    void deletePolicyOwnerById(int id, int userAccountId) throws Exception;

    PolicyOwner getPolicyOwnerById(int id);

    void updatePolicyOwner(UpdatePolicyOwnerRequest request, int userAccountId) throws Exception;
}
