package examples.chaineddependencyinjection;

import framework.adnotations.Inject;

public class Distributor {
    Coil coil;

    @Inject()
    public Distributor(Coil coil) {
        this.coil = coil;
    }
}
