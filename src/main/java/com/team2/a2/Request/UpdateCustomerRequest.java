package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

public class UpdateCustomerRequest {

    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public UpdateCustomerRequest(int id, String name, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
