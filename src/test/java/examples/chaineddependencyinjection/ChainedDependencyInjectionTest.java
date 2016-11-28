package examples.chaineddependencyinjection;

import framework.AbstractInjectService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChainedDependencyInjectionTest {
    private Car car;

    @Before
    public void setUp() {
        AbstractInjectService injectService = new CarService();
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