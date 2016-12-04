package examples.chaineddependencyinjection;

import framework.Inject;

public abstract class AbstractCylinder {
    Piston piston;

    @Inject()
    public AbstractCylinder(Piston piston) {
        this.piston = piston;
    }
}
