package framework;

import framework.annotations.Inject;
import framework.resolvers.Resolver;

import javax.swing.text.html.Option;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return resolveImplementationAndGetInstance(tClass);
    }

    final <T> T createInstanceWithRequiredDependencies(Class<T> tClass, String name) {
        return resolveImplementationAndGetInstance(tClass, name);
    }

    private final <T> T resolveImplementationAndGetInstance(Class<T> tClass){
        Optional<Binding> oBinding = bindingContainer.getUnnamedBindingToOtherClass(tClass);
        if (oBinding.isPresent()) {
            Class<T> dependencyClass = oBinding.get().getDependencyClass();
            return resolveImplementationAndGetInstance(dependencyClass);
        }
        else {
            return resolveIfSingletonAndGetInstance(tClass);
        }
    }

    private final <T> T resolveImplementationAndGetInstance(Class<T> tClass, String name){
        Optional<Binding> oBinding = bindingContainer.getNamedBindingToOtherClass(tClass, name);
        if (oBinding.isPresent()) {
            Class<T> dependencyClass = oBinding.get().getDependencyClass();
            return resolveImplementationAndGetInstance(dependencyClass);
        }
        else {
            return resolveIfSingletonAndGetInstance(tClass);
        }
    }

    private <T> T resolveIfSingletonAndGetInstance(Class<T> tClass) {
        bindingContainer.addSingletonAnnotationIfExists(tClass);
        Optional<Binding> oBinding = bindingContainer.getSingletonBinding(tClass);
        if (oBinding.isPresent()) {
            if (bindingContainer.getSingletons().get(oBinding.get()) != null) {
                return (T) bindingContainer.getSingletons().get(oBinding.get());
            } else {
                T singleton = resolveIfCollectionAndGetInstance(tClass);
                bindingContainer.getSingletons().put(oBinding.get(),singleton);
                return singleton;
            }
        }
        return resolveIfCollectionAndGetInstance(tClass);
    }

    private final <T> T resolveIfCollectionAndGetInstance(Class<T> tClass) {
        Optional<Binding> oBinding = bindingContainer.getCollectionBinding(tClass);
        if(oBinding.isPresent()){
            return (T)bindingContainer.getCollections().get(oBinding.get());
        }
        return getInstance(tClass);
    }

    private final <T> T getInstance(Class<T> tClass) {
        Constructor<T> constructor = resolver.resolveConstructor(tClass);
        ArrayList<Object> requiredParams = getRequiredParams(constructor, tClass);
        HashMap<Field, Object> requiredFields = getRequiredFields(tClass);
        T result = objectBuilder.createObjectFromConstructorAndParams(constructor, requiredParams);
        objectBuilder.setObjectFields(result, requiredFields);
        return result;
    }

    private final <T> ArrayList<Object> getRequiredParams(Constructor<T> constructor, Class<T> tClass) {
        Class<?>[] params = resolver.resolveConstructorParams(constructor);
        ArrayList<Object> requiredParams = new ArrayList<>();
        for (Class param : params) {
            Object object;
            this.cycleDetector.addEdge(tClass, param);
            object = createInstanceWithRequiredDependencies(param);
            requiredParams.add(object);
        }
        return requiredParams;
    }

    private <T> HashMap<Field, Object> getRequiredFields(Class<T> tClass) {
        HashMap<Field, Object> requiredFields = new HashMap();
        for (Field f : tClass.getFields()) {
            if (f.isAnnotationPresent(Inject.class)) {
                requiredFields.put(f, createInstanceWithRequiredDependencies(f.getType()));
            }
        }
        return requiredFields;
    }
}
