package com.team2.a2.Request;

public class UpdateProviderRequest {

    private int id;
    private int insuranceManagerId;
    private String companyName;
    private String companyAddress;
    private String phoneNumber;
    private String email;
    private String name;

    //constructor for manager
    public UpdateProviderRequest(int id, String companyName, String companyAddress, String phoneNumber, String email, String name) {
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    //constructor for surveyor
    public UpdateProviderRequest(int id, String companyName, String companyAddress, String phoneNumber, String email, String name, int insuranceManagerId) {
        this.id = id;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.insuranceManagerId = insuranceManagerId;
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

    public int getInsuranceManagerId() {
        return insuranceManagerId;
    }
}
