package examples.noinjectioncreation;

import framework.BindingContainer;
import framework.InjectService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NoInjectionCreationTest {
    @Test
    public void testGetCarFromSimpleConstructorInjection() throws Exception {
        InjectService injectService = new InjectService(new BindingContainer());
        Car car = injectService.getObjectInstance(Car.class);
        assertNotNull(car);
    }

}