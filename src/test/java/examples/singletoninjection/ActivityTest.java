package examples.singletoninjection;

import framework.BindingContainer;
import framework.InjectService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;


public class ActivityTest {
    List<Activity> activities;
    @Before
    public void getActivities() throws IllegalAccessException, InstantiationException {
        final int ACTIVITIES_NUMBER = 2;
        activities = new ArrayList<>();
        InjectService injectService = new InjectService(new BindingContainer());
        for (int i = 0; i < ACTIVITIES_NUMBER; i++) {
            activities.add(injectService.getObjectInstance(Activity.class));
        }
    }

    @Test
    public void testSingletonConstructorInjection() throws Exception {
        getActivities();
        Activity firstActivity = activities.get(0);
        Activity secondActivity = activities.get(1);
        assertSame(firstActivity.getLogService(),secondActivity.getLogService());
    }
}