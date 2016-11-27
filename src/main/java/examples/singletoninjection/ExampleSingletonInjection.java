package examples.singletoninjection;

import framework.AbstractInjectService;

import java.util.ArrayList;
import java.util.List;

class ExampleSingletonInjection {
    static List<Activity> getActivities() {
        final int ACTIVITIES_NUMBER = 2;
        List<Activity> activities = new ArrayList<>();
        AbstractInjectService injectService = new ActivitySingletonService();
        for (int i = 0; i < ACTIVITIES_NUMBER; i++) {
            activities.add(injectService.getObjectInstance(Activity.class));
        }
        return activities;
    }
}
