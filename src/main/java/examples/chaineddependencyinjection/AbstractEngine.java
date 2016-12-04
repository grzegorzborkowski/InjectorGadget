package examples.chaineddependencyinjection;

import framework.Inject;
import lombok.Getter;

public abstract class AbstractEngine {
    private Distributor distributor;
    @Getter
    private AbstractCylinder cylinder;

    @Inject()
    public AbstractEngine(Distributor distributor, AbstractCylinder cylinder) {
        this.distributor = distributor;
        this.cylinder = cylinder;
    }
}
