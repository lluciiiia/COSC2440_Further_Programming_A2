package com.team2.a2.Model.User;

import com.team2.a2.Model.Enum.AccountType;

public class Admin extends Account {
    public Admin(int id, String username, String password, AccountType type) {
        super(id, username, password, type);
    }
    //initializer

}
