package com.team2.a2.Entity.User;

abstract public class User {
    protected String username;
    protected String password;

    //default constructor
    public User () {
        this.username = "default";
        this.password = "default";
    }

    //initializer
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getter function
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setter function
    public void setPassword(String password) {
        this.password = password;
    }
}
