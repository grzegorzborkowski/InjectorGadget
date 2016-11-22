package examples.simpleconstructorinjection;

import framework.AbstractInjectService;

class EngineModule extends AbstractInjectService {
    @Override
    public void configure() {
        addBinding(Engine.class, PetrolEngine.class);
    }
}
