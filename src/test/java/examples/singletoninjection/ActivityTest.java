package examples.singletoninjection;

import framework.AbstractInjectService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Activities returns two different Activity objects.
 * Activity object has a LogService instance injected, which is a singleton.
 * Checks if LogService of those Activites is indeed the same object.
 */
public class ActivityTest {

    @Before
    static List<Activity> getActivities() {
        final int ACTIVITIES_NUMBER = 2;
        List<Activity> activities = new ArrayList<>();
        AbstractInjectService injectService = new ActivitySingletonService();
        for (int i = 0; i < ACTIVITIES_NUMBER; i++) {
            activities.add(injectService.getObjectInstance(Activity.class));
        }
        return activities;
    }

    @Test
    public void testSingletonConstructorInjection() throws Exception {
        List<Activity> activities = getActivities();
        Activity firstActivity = activities.get(0);
        Activity secondActivity = activities.get(1);
        assertTrue(firstActivity.getLogService() == secondActivity.getLogService());
    }
}