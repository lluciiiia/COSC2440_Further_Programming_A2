package com.team2.a2.Facade;

import com.team2.a2.Model.User.Customer.PolicyOwner;
import com.team2.a2.Request.InsertPolicyOwnerRequest;

import java.util.List;

public interface PolicyOwnerFacade {

    public PolicyOwner getPolicyOwnerByAccountId(int id);

    List<PolicyOwner> getAllPolicyOwners();

    void createPolicyOwner(InsertPolicyOwnerRequest request);
}
