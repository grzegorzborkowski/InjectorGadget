package framework;

import framework.resolvers.Resolver;

public class InjectService {
    private final Resolver resolver;
    private BindingContainer bindingContainer;
    private ObjectFactory objectFactory;
    private CycleDetector cycleDetector;

    public InjectService(BindingContainer bindingContainer) {
        this.bindingContainer = bindingContainer;
        this.resolver = new Resolver();
        this.cycleDetector = new CycleDetector(bindingContainer.getBindings());
        this.objectFactory = new ObjectFactory(bindingContainer, resolver, cycleDetector);
        this.cycleDetector.checkCycle();
    }

    public final <T> T getObjectInstance(Class<T> tClass) {
        this.bindingContainer.configure();
        return objectFactory.createInstanceWithRequiredDependencies(tClass);
    }

    // TODO: implement
    public final <T> T getObjectInstance(Class<T> tClass, String name) {
        return getObjectInstance(tClass);
    }
}
