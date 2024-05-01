package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Model.User.User;

import java.sql.Connection;

public class UserRepository {
    private Connection connection;
    public UserRepository() {
        this.connection = ConnectionManager.getConnection();
    }
    public User getUser(String userName, String password) {
        return null;
    }
}
