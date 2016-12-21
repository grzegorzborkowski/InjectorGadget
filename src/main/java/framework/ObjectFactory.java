package framework;

import framework.annotations.Inject;
import framework.resolvers.Resolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class ObjectFactory {
    private BindingContainer bindingContainer;
    private Resolver resolver;

    public ObjectFactory(BindingContainer bindingContainer, Resolver resolver) {
        this.bindingContainer = bindingContainer;
        this.resolver = resolver;
    }

    final <T> T createObjectFromConstructorAndParams(Constructor<T> constructor, List<Object> requiredParams) {
        Object[] requiredParamsArray = new Object[requiredParams.size()];
        requiredParamsArray = requiredParams.toArray(requiredParamsArray);
        try {
            return constructor.newInstance(requiredParamsArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                T singleton = getInstance(tClass);
                bindingContainer.getBindings().get(tClass).setInstance(singleton);
                return singleton;
            }
        }
        return getInstance(tClass);
    }

    private final <T> T getInstance(Class<T> tClass) {
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
            T result = createObjectFromConstructorAndParams(constructor, requiredParams);
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
