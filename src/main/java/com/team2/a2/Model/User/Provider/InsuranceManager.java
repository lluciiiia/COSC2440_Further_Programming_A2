package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

import java.util.ArrayList;
import java.util.List;

public class InsuranceManager extends Provider{
    private List<InsuranceSurveyor> insuranceSurveyors;

    public InsuranceManager(int id, String username, String password, String companyName, String companyAddress, String providerPhone, String providerEmail, String providerID, String providerName, AccountType type, List<InsuranceSurveyor> insuranceSurveyors) {
        super(id, username, password, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName, type);
        this.insuranceSurveyors = insuranceSurveyors;
    }

    //getter function
    public List<InsuranceSurveyor> getInsuranceSurveyors() {
        return insuranceSurveyors;
    }
}
