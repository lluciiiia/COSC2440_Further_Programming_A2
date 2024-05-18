package org.example;

import com.team2.a2.ConnectionManager;
import com.team2.a2.Controller.LogController;
import com.team2.a2.Model.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class LogControllerTest {
    private LogController logController;

    @BeforeAll
    public void setUp() {
        ConnectionManager.initConnection();
        logController = new LogController();
    }

    @Test
    public void testGetLogsByAccountId() {
        int userAccountId = 1;

        List<Log> logs = logController.getLogsByAccountId(userAccountId);

        assertNotNull(logs, "The returned list should not be null");

    }
}
