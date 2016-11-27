package examples.singletoninjection;

import framework.Inject;

public class Activity {
    private LogService logService;

    @Inject(bindingName = "")
    public Activity(LogService logService) {
        this.logService = logService;
    }

    public LogService getLogService() {
        return logService;
    }
}
