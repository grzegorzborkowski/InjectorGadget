package examples.namedbinding;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class NamedBindingInjectionTest {
    @Test
    public void getAccomodationFromNamedBindingInjection() throws Exception {
        Accomodation accomodation = NamedBindingInjection.getAccomodationFromNamedBindingInjection();
        assertThat(accomodation, instanceOf(Hotel.class));
    }

}