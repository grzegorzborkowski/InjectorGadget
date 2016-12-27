package framework;

import framework.annotations.Singleton;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class BindingContainer {
    @Getter
    private Map<Class, Binding> bindings;

    public BindingContainer() {
        this.bindings = new HashMap<>();
    }

    protected final void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (bindings.containsKey(source)) {
            Binding binding = bindings.get(source);
            binding.setDependencyClass(dest);
        } else {
            Binding binding = new Binding();
            binding.setDependencyClass(dest);
            bindings.put(source, binding);
        }
    }

    protected final void addBindingWithName(Class source, Class dest, String name) {
    }

    final void addSingletonAnnotationIfExists(Class source) {
        if (source.isAnnotationPresent(Singleton.class)) {
            if (bindings.get(source) == null) {
                this.bindings.put(source, new Binding(Scope.SINGLETON));
            } else {
                bindings.get(source).setSingleton();
            }
        }
    }

    final boolean containsBindingToOtherClass(Class source) {
        return getBindings().containsKey(source) && getBindings().get(source).getDependencyClass() != null;
    }

    final boolean containsSingletonBinding(Class source) {
        return getBindings().get(source) != null && getBindings().get(source).getScope() == Scope.SINGLETON;
    }

    public void configure() {
    }

}
