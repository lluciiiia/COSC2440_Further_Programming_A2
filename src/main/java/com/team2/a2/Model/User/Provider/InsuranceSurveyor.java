package com.team2.a2.Model.User.Provider;

import com.team2.a2.Model.Enum.AccountType;

public class InsuranceSurveyor extends Provider{

    public InsuranceSurveyor(int id, String username, String password, String companyName, String companyAddress, String providerPhone, String providerEmail, String providerID, String providerName, AccountType type) {
        super(id, username, password, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName, type);
    }
}
