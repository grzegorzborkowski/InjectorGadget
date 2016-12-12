package examples.simpleconstructorinjection;

import framework.BindingContainer;

public class EngineBindingContainer extends BindingContainer {
    @Override
    public void configure() {
        addBinding(Engine.class, PetrolEngine.class);
    }
}
