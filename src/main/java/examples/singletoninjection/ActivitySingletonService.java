package examples.singletoninjection;

import framework.AbstractInjectService;

public class ActivitySingletonService extends AbstractInjectService {

    @Override
    public void configure() {
        addBinding(Activity.class, LogService.class);
    }
}
