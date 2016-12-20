package framework.resolvers;

import framework.Binding;
import framework.adnotations.Inject;
import framework.exceptions.AmbigiousConstructorException;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.Map;

public class Resolver {
    @Getter
    private CycleResolver cycleResolver;

    public Resolver(Map<Class, Binding> bindings) {
        cycleResolver = new CycleResolver(bindings);
    }

    public final <T> Constructor<T> resolveConstructor(Class<T> tClass) {
        Constructor<T>[] constructors = (Constructor<T>[]) tClass.getConstructors();
        int constructorNumber = 0;
        Constructor<T> foundConstructor = null;
        for (Constructor<T> c : constructors) {
            if (c.isAnnotationPresent(Inject.class)) {
                constructorNumber += 1;
                foundConstructor = c;
            }
        }
        if (constructorNumber > 1) {
            throw new AmbigiousConstructorException("There is more than one constructor with @Inject annotation in this class.");
        } else if (constructorNumber == 1) {
            return foundConstructor;
        }
        try {
            return tClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final <T> Class<?>[] resolveConstructorParams(Constructor<T> constructor) {
        return constructor.getParameterTypes();
    }

    public void checkCycle() {
        cycleResolver.checkCycle();
    }
}