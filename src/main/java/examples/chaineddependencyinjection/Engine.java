package examples.chaineddependencyinjection;

import framework.Inject;

public class Engine extends AbstractEngine {
    @Inject
    public Engine(Distributor distributor, Cylinder cylinder) {
        super(distributor, cylinder);
    }
}
