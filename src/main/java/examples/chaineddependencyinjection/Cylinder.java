package examples.chaineddependencyinjection;

import framework.annotations.Inject;

public class Cylinder extends AbstractCylinder {
    @Inject
    public Cylinder(Piston piston) {
        super(piston);
    }
}
