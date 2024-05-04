package com.team2.a2.Model.User;

abstract public class User {
    public Integer id;
    protected String username;
    protected String password;

    // Default Constructor
    public User() {
        this.username = "";
        this.password = "";
    }

    //initializer
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getter function
    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    //setter function
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) { this.id = id; }

    public void setUsername(String username) { this.username = username;}
}
