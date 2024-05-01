package com.team2.a2.Entity.User.Provider;

import java.util.ArrayList;
import java.util.List;

public class InsuranceManager extends Provider{
    private List<InsuranceSurveyor> insuranceSurveyors;

    //default constructor
    public InsuranceManager() {
        super();
        insuranceSurveyors = new ArrayList<>();
    }

    //initializer
    public InsuranceManager(String username, String password, String companyName, String companyAddress,
                            String providerPhone, String providerEmail, String providerID, String providerName,
                            List<InsuranceSurveyor> insuranceSurveyors) {
        super(username, password, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName);
        this.insuranceSurveyors = insuranceSurveyors;
    }

    //getter function
    public List<InsuranceSurveyor> getInsuranceSurveyors() {
        return insuranceSurveyors;
    }
}
