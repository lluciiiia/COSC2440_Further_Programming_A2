package com.team2.a2.Facade;

/**
 * @author <Team 2>
 */

import com.team2.a2.Model.Log;

import java.util.List;

public interface LogFacade {
    public List<Log> getLogsByAccountId(int accountId);
}
