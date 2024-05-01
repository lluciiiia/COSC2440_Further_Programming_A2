package com.team2.a2.Controller;

import com.team2.a2.Request.LoginRequest;
import com.team2.a2.Service.UserService;

public class UserController {
    public UserService userService;

    public boolean login(LoginRequest request) {
        return userService.login(request);
    }
}
