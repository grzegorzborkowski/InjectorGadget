package examples.noinjectioncreation;

import framework.AbstractInjectService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NoInjectionCreationTest {
    @Test
    public void testGetCarFromSimpleConstructorInjection() throws Exception {
        AbstractInjectService injectService = new CarService();
        Car car = injectService.getObjectInstance(Car.class);
        assertNotNull(car);
    }

}