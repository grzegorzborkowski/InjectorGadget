package framework;

import framework.resolvers.Resolver;

public class InjectService {
    // TODO: InjectService should have a dependency to CycleResolver instead of
    // TODO: how it's now that resolver has a dependency to CycleResolver
    private final Resolver resolver;
    private BindingContainer bindingContainer;
    private ObjectFactory objectFactory;

    public InjectService(BindingContainer bindingContainer) {
        this.bindingContainer = bindingContainer;
        this.resolver = new Resolver(bindingContainer.getBindings());
        this.objectFactory = new ObjectFactory(bindingContainer, resolver);
        resolver.checkCycle();
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
