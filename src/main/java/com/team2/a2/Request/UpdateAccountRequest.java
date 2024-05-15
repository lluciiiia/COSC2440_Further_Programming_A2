package com.team2.a2.Request;

public class UpdateAccountRequest {
    int id;
    String username;
    String password;

    public UpdateAccountRequest(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}