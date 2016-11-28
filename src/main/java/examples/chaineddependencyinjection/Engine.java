package examples.chaineddependencyinjection;

public class Engine extends AbstractEngine {
    public Engine(Distributor distributor, Cylinder cylinder) {
        super(distributor, cylinder);
    }
}
