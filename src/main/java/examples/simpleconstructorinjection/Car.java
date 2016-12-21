package examples.simpleconstructorinjection;

import framework.annotations.Inject;
import lombok.Getter;

public class Car {
    @Getter
    private Engine engine;

    @Inject()
    public Car(final Engine engine) {
        this.engine = engine;
    }
}
