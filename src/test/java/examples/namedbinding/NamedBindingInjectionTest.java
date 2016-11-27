package examples.namedbinding;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class NamedBindingInjectionTest {
    @Test
    public void getAccomodationFromNamedBindingInjection() throws Exception {
        Accommodation accommodation = NamedBindingInjection.getAccomodationFromNamedBindingInjection();
        assertThat(accommodation, instanceOf(Hotel.class));
    }

}