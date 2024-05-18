package com.team2.a2.Request;

public class IdRequest {
    int id;
    int accountId;

    public IdRequest(int id, int accountId) {
        this.id = id;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }
    public int getAccountId() {
        return accountId;
    }
}
