package examples.chaineddependencyinjection;

import framework.AbstractInjectService;

public class CarService extends AbstractInjectService {

    @Override
    public void configure() {
        addBinding(AbstractEngine.class, Engine.class);
        addBinding(AbstractCylinder.class, Cylinder.class);
    }
}
