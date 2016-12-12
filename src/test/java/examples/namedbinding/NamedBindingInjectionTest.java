package examples.namedbinding;

import framework.InjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class NamedBindingInjectionTest {
    @Test
    public void getAccomodationFromNamedBindingInjection() throws Exception {
        InjectService injectService = new InjectService(new TripService());
        Accommodation accommodation = injectService.getObjectInstance(Trip.class).getAccommodation();
        assertThat(accommodation, instanceOf(Hotel.class));
    }

}