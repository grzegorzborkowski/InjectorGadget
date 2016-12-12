package examples.chaineddependencyinjection;

import framework.BindingContainer;

public class CarBindingContainer extends BindingContainer {
    @Override
    public void configure() {
        addBinding(AbstractEngine.class, Engine.class);
        addBinding(AbstractCylinder.class, Cylinder.class);
    }
}
