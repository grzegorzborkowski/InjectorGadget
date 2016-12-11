package examples.chaineddependencyinjection;

import framework.Inject;

public class Cylinder extends AbstractCylinder {
    @Inject
    public Cylinder(Piston piston) {
        super(piston);
    }
}
