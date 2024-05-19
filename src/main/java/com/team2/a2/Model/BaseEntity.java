package com.team2.a2.Model;

/**
 * @author <Team 2>
 */

import java.util.Date;

public abstract class BaseEntity {
    private int id;

    private Date createdAt;

    private Date updatedAt;

    //initializer
    public BaseEntity(int id, Date createdAt, Date updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
