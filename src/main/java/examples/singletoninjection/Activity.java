package examples.singletoninjection;

import framework.Inject;

public class Activity {
    private LogService logService;

    @Inject
    public Activity(LogService logService) {
        this.logService = logService;
    }

    public LogService getLogService() {
        return logService;
    }
}
