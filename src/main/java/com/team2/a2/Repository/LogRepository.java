package com.team2.a2.Repository;

import com.team2.a2.ConnectionManager;

import java.sql.Connection;

public class LogRepository {

    private Connection connection;

    public LogRepository() {
        this.connection = ConnectionManager.getConnection();
    }



}
