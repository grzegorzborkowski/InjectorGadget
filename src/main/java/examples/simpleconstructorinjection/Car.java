package examples.simpleconstructorinjection;

import framework.Inject;

class Car {
    private Engine engine;

    @Inject(bindingName = "")
    public Car(Engine engine) {
        this.engine = engine;
    }

    Engine getEngine() {
        return engine;
    }
}
