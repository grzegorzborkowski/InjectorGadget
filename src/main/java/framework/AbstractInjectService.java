package framework;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

abstract public class AbstractInjectService {
    private BindingContainer bindingContainer;

    public AbstractInjectService() {
        this.bindingContainer = new BindingContainer();
    }

    protected void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (bindingContainer.getBindings().containsKey(source)) {
            Binding binding = bindingContainer.getBindings().get(source);
            List<Class> classList = binding.getClassList();
            classList.add(dest);
        } else {
            Binding binding = new Binding();
            binding.getClassList().add(dest);
            bindingContainer.getBindings().put(source, binding);
        }
    }

    protected void addBindingWithName(Class source, Class dest, String name) {

    }

    public void configure() {
    }

    public <T> T getObjectInstance(Class<T> tClass) {
        configure();
        return resolveIfSingletonAndGetInstance(tClass);
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
            return constructor.newInstance(requiredParams.toArray(new Object[requiredParams.size()]));
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
