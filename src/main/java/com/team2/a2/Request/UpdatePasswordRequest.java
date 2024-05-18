package com.team2.a2.Request;

public class UpdatePasswordRequest {
    int id;
    String password;

    public UpdatePasswordRequest(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() { return id; }

    public String getPassword() {
        return password;
    }
}