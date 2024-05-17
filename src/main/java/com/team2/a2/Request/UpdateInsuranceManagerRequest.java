package com.team2.a2.Request;

public class UpdateInsuranceManagerRequest {

    private int id;
    private String companyName;
    private String companyAddress;
    private String phoneNumber;
    private String email;
    private String name;

    public UpdateInsuranceManagerRequest(int id, String companyName, String companyAddress, String phoneNumber, String email, String name) {
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    public int getId() { return id; }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
