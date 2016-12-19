package framework;

import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class BindingContainer {
    @Getter
    private Map<Class, Binding> bindings;
    @Getter
    private Map<Class, Constructor> constructorMap;
    @Getter
    private Map<Class, Object> singletons;

    public BindingContainer() {
        this.bindings = new HashMap<>();
        this.constructorMap = new HashMap<>();
        this.singletons = new HashMap<>();
    }

    protected void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (bindings.containsKey(source)) {
            Binding binding = bindings.get(source);
            List<Class> classList = binding.getClassList();
            classList.add(dest);
        } else {
            Binding binding = new Binding();
            binding.getClassList().add(dest);
            bindings.put(source, binding);
        }
    }

    protected void addBindingWithName(Class source, Class dest, String name) {
    }

    public void configure() {
    }

}