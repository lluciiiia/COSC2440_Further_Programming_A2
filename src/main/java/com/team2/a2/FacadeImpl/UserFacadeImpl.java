package com.team2.a2.FacadeImpl;

import com.team2.a2.DatabaseUtil;
import com.team2.a2.Facade.UserFacade;
import com.team2.a2.Model.User.User;
import com.team2.a2.Repository.UserRepository;
import com.team2.a2.Request.LoginRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserFacadeImpl implements UserFacade {

    UserRepository userRepository;

    public boolean login(LoginRequest request) {
        User user = userRepository.getUser(request.getUsername(), request.getPassword());
        return user != null;
    }

}
