package examples.chaineddependencyinjection;

import framework.annotations.Inject;
import lombok.Getter;

public class Car {

    @Getter
    private AbstractEngine engine;
    private SteeringWheel steeringWheel;

    @Inject()
    public Car(final AbstractEngine engine, final SteeringWheel steeringWheel) {
        this.engine = engine;
        this.steeringWheel = steeringWheel;
    }

}
