package framework;


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

abstract public class AbstractInjectService {

    private Map<Class, List<Class>> bindings;
    private Map<Class, Constructor> constructorMap;

    public AbstractInjectService() {
        this.bindings = new HashMap<>();
        this.constructorMap = new HashMap<>();
    }

    protected void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (bindings.containsKey(source)) {
            List<Class> bind = bindings.get(source);
            bind.add(dest);
            bindings.replace(source, bind);
        } else {
            List<Class> destList = new ArrayList<>();
            destList.add(dest);
            bindings.put(source, destList);
        }
    }

    protected void addBindingWithName(Class source, Class dest, String name) {

    }

    public void configure() {
    }

    public <T> T getObjectInstance(Class<T> tClass) {
        configure();
        return getInstance(tClass);
    }

    private <T> T getInstance(Class<T> tClass) {
        Constructor<T> constructor = resolveConstructor(tClass);
        Class<?>[] params = resolveConstructorParams(constructor);
        ArrayList<Object> requiredParams = new ArrayList<>();
        for (Class param : params) {
            try {
                Object object;
                if (bindings.containsKey(param)) {
                    List<Class> binded = bindings.get(param);
                    for (Class c : binded) {
                        requiredParams.add(getInstance(c));
                    }
                } else {
                    object = getInstance(param);
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
        if(constructorMap.containsKey(tClass)){
            return constructorMap.get(tClass);
        }
        else{
            for (Constructor<T> c : constructors)
            {
                if (c.isAnnotationPresent(Inject.class))
                {
                    constructorMap.put(tClass, c);
                    return c;
                }
            }
        }
        // Should be changed to null but right now it requires modifying the tests.
        return constructors[0];
    }

    private <T> Class<?>[] resolveConstructorParams(Constructor<T> constructor) {
        return constructor.getParameterTypes();
    }





}
