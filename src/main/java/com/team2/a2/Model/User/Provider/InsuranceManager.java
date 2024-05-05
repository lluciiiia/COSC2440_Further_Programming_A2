package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceManager extends Provider{
    private List<InsuranceSurveyor> insuranceSurveyors;


    public InsuranceManager(int id, Date createdAt, Date updatedAt, int accountId, String companyName, String companyAddress, String providerPhone, String providerEmail, String providerID, String providerName, List<InsuranceSurveyor> insuranceSurveyors) {
        super(id, createdAt, updatedAt, accountId, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName);
        this.insuranceSurveyors = insuranceSurveyors;
    }
}
