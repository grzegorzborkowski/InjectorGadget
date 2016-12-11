package examples.singletoninjection;

import framework.Inject;
import lombok.Getter;

public class Activity {

    public LogService getLogService() {
        return logService;
    }

    private LogService logService;

    @Inject()
    public Activity(LogService logService) {
        this.logService = logService;
    }
}
