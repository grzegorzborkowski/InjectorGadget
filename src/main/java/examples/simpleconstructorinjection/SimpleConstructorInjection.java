package examples.simpleconstructorinjection;

import framework.AbstractInjectService;

class SimpleConstructorInjection {
    static Car getCarFromSimpleConstructorInjection() {
        AbstractInjectService injectService = new EngineService();
        return injectService.getObjectInstance(Car.class);
    }
}
