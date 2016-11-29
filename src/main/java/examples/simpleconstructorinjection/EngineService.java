package examples.simpleconstructorinjection;

import framework.AbstractInjectService;

public class EngineService extends AbstractInjectService {
    @Override
    public void configure() {
        addBinding(Engine.class, PetrolEngine.class);
    }
}
