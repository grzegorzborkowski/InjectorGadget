package examples.simpleconstructorinjection;

import framework.AbstractInjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleConstructorInjectionTest {
    @Test
    public void testGetCarFromSimpleConstructorInjection() throws Exception {
        AbstractInjectService injectService = new EngineService();
        Car car = injectService.getObjectInstance(Car.class);
        assertThat(car.getEngine(), instanceOf(PetrolEngine.class));
    }
}