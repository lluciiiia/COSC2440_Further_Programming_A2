package com.team2.a2.Controller;

import com.team2.a2.Facade.UserFacade;
import com.team2.a2.Request.LoginRequest;

public class UserController {
    private UserFacade userFacade;

    public boolean login(LoginRequest request) {
        return userFacade.login(request);
    }
}
