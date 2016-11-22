package examples.singletoninjection;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ActivityTest {

    @Test
    /**
     * Activities returns two different Activity objects.
     * Activity object has a LogService instance injected, which is a singleton.
     * Checks if LogService of those Activites is indeed the same object.
     */
    public void testSingletonConstructorInjection() throws Exception {
        List<Activity> activities = ExampleSingletonInjection.getActivities();
        Activity firstActivity = activities.get(0);
        Activity secondActivity = activities.get(1);
        assertTrue(firstActivity.getLogService() == secondActivity.getLogService());
    }
}