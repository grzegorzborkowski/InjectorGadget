package examples.fieldinjection;

import framework.InjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldInjectionTest {
    @Test
    public void testFieldInjection() throws Exception {
        InjectService injectService = new InjectService(new UserBindingContainer());
        User user = injectService.getObjectInstance(User.class);
        assertThat(user.optionalInfo, instanceOf(OptionalInfo.class));
    }
}
