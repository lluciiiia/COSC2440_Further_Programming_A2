package com.team2.a2.FacadeImpl;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.LogFacade;
import com.team2.a2.Model.Log;
import com.team2.a2.Repository.LogRepository;

import java.util.List;

public class LogFacadeImpl implements LogFacade {

    LogRepository logRepository;

    public LogFacadeImpl() {
        this.logRepository = new LogRepository();
    }

    @Override
    public List<Log> getLogsByAccountId(int accountId) {
        return logRepository.getLogsByAccountId(accountId);
    }
}


