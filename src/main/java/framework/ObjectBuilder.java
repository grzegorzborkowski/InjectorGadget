package framework;

import framework.annotations.Inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectBuilder {

    public ObjectBuilder() {
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

    final void setObjectFields(Object tObject, HashMap<Field, Object> fields) {
        for (Map.Entry<Field,Object> entry : fields.entrySet()) {
            try {
                entry.getKey().set(tObject, entry.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
