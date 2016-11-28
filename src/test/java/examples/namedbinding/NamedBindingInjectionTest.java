package examples.namedbinding;

import framework.AbstractInjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class NamedBindingInjectionTest {
    @Test
    public void getAccomodationFromNamedBindingInjection() throws Exception {
        AbstractInjectService injectService = new TripService();
        Accommodation accommodation = injectService.getObjectInstance(Trip.class).getAccommodation();
        assertThat(accommodation, instanceOf(Hotel.class));
    }

}