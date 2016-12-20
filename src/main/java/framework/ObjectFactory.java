package framework;

import java.lang.reflect.Constructor;
import java.util.List;

class ObjectFactory {

    public ObjectFactory() {
    }

    <T> T createObjectFromConstructorAndParams(Constructor<T> constructor, List<Object> requiredParams) {
        Object[] requiredParamsArray = new Object[requiredParams.size()];
        requiredParamsArray = requiredParams.toArray(requiredParamsArray);
        try {
            return constructor.newInstance(requiredParamsArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
