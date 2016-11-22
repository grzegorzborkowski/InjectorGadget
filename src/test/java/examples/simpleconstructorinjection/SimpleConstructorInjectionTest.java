package examples.simpleconstructorinjection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class SimpleConstructorInjectionTest {
    @Test
    public void testGetCarFromSimpleConstructorInjection() throws Exception {
        Car car = SimpleConstructorInjection.getCarFromSimpleConstructorInjection();
        assertThat(car.getEngine(), instanceOf(PetrolEngine.class));
    }
}