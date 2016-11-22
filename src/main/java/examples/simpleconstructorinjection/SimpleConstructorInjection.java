package examples.simpleconstructorinjection;

import framework.AbstractInjectService;

class SimpleConstructorInjection {
    static Car getCarFromSimpleConstructorInjection() {
        AbstractInjectService injectService = new EngineModule();
        return injectService.getObjectInstance(Car.class);
    }
}
