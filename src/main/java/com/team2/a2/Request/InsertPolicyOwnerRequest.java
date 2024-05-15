package com.team2.a2.Request;

public class InsertPolicyOwnerRequest {
    private String username;
    private String password;
    private String name;

    public InsertPolicyOwnerRequest(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
