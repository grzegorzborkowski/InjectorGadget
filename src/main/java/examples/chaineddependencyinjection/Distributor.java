package examples.chaineddependencyinjection;

import framework.Inject;

public class Distributor {
    Coil coil;

    @Inject(bindingName = "")
    public Distributor(Coil coil) {
        this.coil = coil;
    }
}
