package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

public class InsertInsuranceManagerRequest {

    private String username;
    private String password;
    private String companyName;
    private String companyAddress;
    private String phoneNumber;
    private String email;
    private String name;

    public InsertInsuranceManagerRequest(String username, String password, String companyName, String companyAddress, String phoneNumber, String email, String name) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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
