package framework;

import framework.annotations.Inject;
import framework.resolvers.Resolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * TODO: ObjectFactory should have a dependency to BINDING
 * TODO: instead of to BindingContainer.
 * TODO: InjectService has bindingContainer and provides to ObjectFactory exact binding and it creates object from that
 * TODO: implement a new feature: - dependency to collection, -injection through field -named binding -dependency to already created objects
 */
class ObjectFactory {
    private BindingContainer bindingContainer;
    private Resolver resolver;
    private CycleDetector cycleDetector;
    private ObjectBuilder objectBuilder;


    public ObjectFactory(BindingContainer bindingContainer, Resolver resolver, CycleDetector cycleDetector) {
        this.bindingContainer = bindingContainer;
        this.resolver = resolver;
        this.cycleDetector = cycleDetector;
        this.objectBuilder = new ObjectBuilder();
    }

    final <T> T createInstanceWithRequiredDependencies(Class<T> tClass) {
        return resolveIfSingletonAndGetInstance(tClass);
    }

    private <T> T resolveIfSingletonAndGetInstance(Class<T> tClass) {
        bindingContainer.addSingletonAnnotationIfExists(tClass);
        if (bindingContainer.containsSingletonBinding(tClass)) {
            if (bindingContainer.getBindings().get(tClass).getInstance() != null) {
                return (T) bindingContainer.getBindings().get(tClass).getInstance();
            } else {
                T singleton = resolveIfCollectionAndGetInstance(tClass);
                bindingContainer.getBindings().get(tClass).setInstance(singleton);
                return singleton;
            }
        }
        return resolveIfCollectionAndGetInstance(tClass);
    }

    private final <T> T resolveIfCollectionAndGetInstance(Class<T> tClass) {
        if(bindingContainer.containsCollectionBinding(tClass)){
            return (T)bindingContainer.getBindings().get(tClass).getInstance();
        }
        return resolveImplementationAndGetInstance(tClass);
    }

    private final <T> T resolveImplementationAndGetInstance(Class<T> tClass){
        if (bindingContainer.containsBindingToOtherClass(tClass)) {
            Class<T> dependencyClass = bindingContainer.getBindings().get(tClass).getDependencyClass();
            return resolveImplementationAndGetInstance(dependencyClass);
        }
        else {
            return getInstance(tClass);
        }
    }

    private final <T> T getInstance(Class<T> tClass) {
        Constructor<T> constructor = resolver.resolveConstructor(tClass);
        Class<?>[] params = resolver.resolveConstructorParams(constructor);
        ArrayList<Object> requiredParams = new ArrayList<>();
        for (Class param : params) {
            Object object;
            this.cycleDetector.addEdge(tClass, param);
            object = createInstanceWithRequiredDependencies(param);
            requiredParams.add(object);
        }
        T result = objectBuilder.createObjectFromConstructorAndParams(constructor, requiredParams);
        setObjectProperties(tClass, result);
        return result;
    }

    private <T> void setObjectProperties(Class<T> tClass, Object tObject) {
        for (Field f : tClass.getFields()) {
            if (f.isAnnotationPresent(Inject.class)) {
                try {
                    f.set(tObject, createInstanceWithRequiredDependencies(f.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
