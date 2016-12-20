package framework;

import framework.adnotations.Inject;
import framework.resolvers.Resolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class InjectService {
    private final Resolver resolver;
    private BindingContainer bindingContainer;
    private ObjectFactory objectFactory;

    public InjectService(BindingContainer bindingContainer) {
        this.bindingContainer = bindingContainer;
        this.resolver = new Resolver(bindingContainer.getBindings());
        this.objectFactory = new ObjectFactory();
        resolver.checkCycle();
    }

    public <T> T getObjectInstance(Class<T> tClass) {
        this.bindingContainer.configure();
        return resolveIfSingletonAndGetInstance(tClass);
    }

    private <T> T resolveIfSingletonAndGetInstance(Class<T> tClass) {
        bindingContainer.addSingletonAnnotationIfExists(tClass);
        if (bindingContainer.containsSingletonBinding(tClass)) {
            if (bindingContainer.getBindings().get(tClass).getInstance() != null) {
                return (T) bindingContainer.getBindings().get(tClass).getInstance();
            } else {
                T singleton = getInstance(tClass);
                bindingContainer.getBindings().get(tClass).setInstance(singleton);
                return singleton;
            }
        }
        return getInstance(tClass);
    }

    private <T> T getInstance(Class<T> tClass) {
        if (bindingContainer.containsBindingToOtherClass(tClass)) {
            Class<T> dependencyClass = bindingContainer.getBindings().get(tClass).getDependencyClass();
            return getInstance(dependencyClass);
        }
        Constructor<T> constructor = resolver.resolveConstructor(tClass);
        Class<?>[] params = resolver.resolveConstructorParams(constructor);
        ArrayList<Object> requiredParams = new ArrayList<>();
        for (Class param : params) {
                Object object;
                if (bindingContainer.containsBindingToOtherClass(param)) {
                    Class binded = bindingContainer.getBindings().get(param).getDependencyClass();
                    requiredParams.add(resolveIfSingletonAndGetInstance(binded));
                } else {
                    resolver.getCycleResolver().addEdge(tClass, param);
                    object = resolveIfSingletonAndGetInstance(param);
                    requiredParams.add(object);
                }
        }
        try {
            T result = objectFactory.createObjectFromConstructorAndParams(constructor, requiredParams);
            setObjectProperties(tClass, result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> void setObjectProperties(Class<T> tClass, Object tObject) {
        for (Field f : tClass.getFields()) {
            if (f.isAnnotationPresent(Inject.class)) {
                try {
                    f.set(tObject, resolveIfSingletonAndGetInstance(f.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
