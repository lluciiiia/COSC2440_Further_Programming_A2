package com.team2.a2.Request;

/**
 * @author <Team 2>
 */

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