package com.team2.a2.Model;

import java.util.Date;

public class Log {
    private int id;

    private int accountId;

    private String content;

    private Date createdAt;

    public Log(int id, int accountId, String content, Date createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
