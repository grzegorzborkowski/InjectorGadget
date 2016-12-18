package framework;

import examples.parameterinjection.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InjectService {
    private BindingContainer bindingContainer;

    public InjectService(BindingContainer bindingContainer) {
        this.bindingContainer = bindingContainer;
    }

    public <T> T getObjectInstance(Class<T> tClass) {
        this.bindingContainer.configure();
        return resolveIfSingletonAndGetInstance(tClass);
    }

    public <T> void setObjectProperties(Class<T> tClass, Object tObject) {
        for(Field f : tClass.getFields()){
            if(f.isAnnotationPresent(Inject.class)){
                try {
                    f.set(tObject, resolveIfSingletonAndGetInstance(f.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> T resolveIfSingletonAndGetInstance(Class<T> tClass){
        if(tClass.isAnnotationPresent(Singleton.class)){
            if (bindingContainer.getSingletons().containsKey(tClass)) {
                return (T) bindingContainer.getSingletons().get(tClass);
            }
            else{
                T singleton = getInstance(tClass);
                bindingContainer.getSingletons().put(tClass, singleton);
                return singleton;
            }
        }
        return getInstance(tClass);
    }

    private <T> T getInstance(Class<T> tClass) {
        if (bindingContainer.getBindings().containsKey(tClass)) {
            tClass = bindingContainer.getBindings().get(tClass).getClassList().get(0);
            return getInstance(tClass);
        }
        Constructor<T> constructor = resolveConstructor(tClass);
        Class<?>[] params = resolveConstructorParams(constructor);
        ArrayList<Object> requiredParams = new ArrayList<>();
        for (Class param : params) {
            try {
                Object object;
                if (bindingContainer.getBindings().containsKey(param)) {
                    List<Class> binded = bindingContainer.getBindings().get(param).getClassList();
                    for (Class c : binded) {
                        requiredParams.add(resolveIfSingletonAndGetInstance(c));
                    }
                } else {
                    object = resolveIfSingletonAndGetInstance(param);
                    requiredParams.add(object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            T result = constructor.newInstance(requiredParams.toArray(new Object[requiredParams.size()]));
            setObjectProperties(tClass, result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> Constructor<T> resolveConstructor(Class<T> tClass) {
        Constructor<T> [] constructors = (Constructor<T>[]) tClass.getConstructors();
        if (bindingContainer.getConstructorMap().containsKey(tClass)) {
            return bindingContainer.getConstructorMap().get(tClass);
        }
        else{
            for (Constructor<T> c : constructors) {
                if (c.isAnnotationPresent(Inject.class)) {
                    bindingContainer.getConstructorMap().put(tClass, c);
                    return c;
                }
            }
        }
        try {
            return tClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> Class<?>[] resolveConstructorParams(Constructor<T> constructor) {
        return constructor.getParameterTypes();
    }
}
