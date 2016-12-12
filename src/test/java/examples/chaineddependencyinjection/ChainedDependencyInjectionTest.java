package examples.chaineddependencyinjection;

import framework.InjectService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChainedDependencyInjectionTest {
    private Car car;

    @Before
    public void setUp() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        InjectService injectService = new InjectService(new CarBindingContainer());
        this.car = injectService.getObjectInstance(Car.class);
    }

    @Test
    public void chainedDITestEngine() throws Exception {
        assertThat(car.getEngine(), instanceOf(Engine.class));
    }

    @Test
    public void chainedDITestCylinder() throws Exception {
        assertThat(car.getEngine().getCylinder(), instanceOf(Cylinder.class));
    }

}