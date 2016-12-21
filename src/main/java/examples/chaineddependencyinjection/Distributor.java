package examples.chaineddependencyinjection;

import framework.annotations.Inject;

public class Distributor {
    Coil coil;

    @Inject()
    public Distributor(Coil coil) {
        this.coil = coil;
    }
}
