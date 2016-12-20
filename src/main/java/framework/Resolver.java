package framework;

import framework.exceptions.AmbigiousConstructorException;

import java.lang.reflect.Constructor;

public class Resolver {

    Resolver() {
    }

    final <T> Constructor<T> resolveConstructor(Class<T> tClass) {
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

    final <T> Class<?>[] resolveConstructorParams(Constructor<T> constructor) {
        return constructor.getParameterTypes();
    }
}