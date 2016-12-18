package examples.parameterinjection;

import framework.InjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by tomek on 18.12.2016.
 */
public class ParameterInjectionTest {
    @Test
    public void testParameterInjection() throws Exception {
        InjectService injectService = new InjectService(new UserBindingContainer());
        User user = injectService.getObjectInstance(User.class);
        assertThat(user.optionalInfo, instanceOf(OptionalInfo.class));
    }
}
