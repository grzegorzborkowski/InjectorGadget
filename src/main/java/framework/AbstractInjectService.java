package framework;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

abstract public class AbstractInjectService {

    private Map<Class, Class> bindings;

    public AbstractInjectService() {
        this.bindings = new HashMap<Class, Class>();
    }

    protected void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        bindings.put(source, dest);
    }

    protected void addBindingWithName(Class source, Class dest, String bindingName) {

    }

    public void configure() {
    }

    public <T> T getObjectInstance(Class<T> tClass) {
        return (T) new Object();
    }
}
