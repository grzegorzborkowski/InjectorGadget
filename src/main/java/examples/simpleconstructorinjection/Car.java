package examples.simpleconstructorinjection;

import framework.Inject;
import lombok.Getter;

public class Car {
    @Getter
    private Engine engine;

    @Inject(bindingName = "")
    public Car(final Engine engine) {
        this.engine = engine;
    }
}
