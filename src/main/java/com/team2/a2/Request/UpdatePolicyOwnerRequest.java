package com.team2.a2.Request;

public class UpdatePolicyOwnerRequest {

    int id;
    private String name;

    public UpdatePolicyOwnerRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

}
