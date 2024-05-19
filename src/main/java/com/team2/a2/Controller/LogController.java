package com.team2.a2.Controller;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.LogFacade;
import com.team2.a2.FacadeImpl.LogFacadeImpl;
import com.team2.a2.Model.Log;

import java.util.List;

public class LogController {
    private LogFacade logFacade;

    public LogController() {
        this.logFacade = new LogFacadeImpl();
    }

    public List<Log> getLogsByAccountId(int accountId) {
        return logFacade.getLogsByAccountId(accountId);
    }
}
