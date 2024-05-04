package com.team2.a2.Model;

import java.util.Date;

public abstract class BaseEntity {
    private int id;

    private Date createdAt;

    private Date updatedAt;

    //initializer
    public BaseEntity(int id) {
        this.id = id;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
