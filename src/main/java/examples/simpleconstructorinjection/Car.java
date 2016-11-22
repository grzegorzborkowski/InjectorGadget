package examples.simpleconstructorinjection;

import framework.Inject;

class Car {
    private Engine engine;

    @Inject
    public Car(Engine engine) {
        this.engine = engine;
    }

    Engine getEngine() {
        return engine;
    }
}
