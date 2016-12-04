package examples.chaineddependencyinjection;

import framework.Inject;

public class Distributor {
    Coil coil;

    @Inject()
    public Distributor(Coil coil) {
        this.coil = coil;
    }
}
