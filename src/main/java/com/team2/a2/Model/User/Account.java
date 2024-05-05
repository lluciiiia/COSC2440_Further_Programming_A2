package com.team2.a2.Model.User;

import com.team2.a2.Model.BaseEntity;
import com.team2.a2.Model.Enum.AccountType;

import java.util.Date;

public class Account extends BaseEntity {

    protected String username;
    protected String password;
    public AccountType type;

    //initializer
    public Account(int id, Date createdAt, Date updatedAt, String username, String password, AccountType type) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.type = type;
    }

    //getter function

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public AccountType getType() { return  type; }

    //setter function
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) { this.username = username;}
}
