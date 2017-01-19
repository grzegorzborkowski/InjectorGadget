package framework;

import com.google.common.collect.ImmutableMap;
import framework.annotations.Singleton;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BindingContainer {
    private Map<Class, Binding> mutableBindings;
    private Map<Binding, Collection> mutableCollections;
    @Getter
    private ImmutableMap<Class, Binding> bindings;
    @Getter
    private Map<Binding, Object> singletons;
    @Getter
    private ImmutableMap<Binding, Collection> collections;

    public BindingContainer() {
        this.mutableBindings = new HashMap<>();
        this.mutableCollections = new HashMap<>();
        this.singletons = new HashMap<>();
        this.configure();
        this.bindings = ImmutableMap.copyOf(mutableBindings);
        this.collections = ImmutableMap.copyOf(mutableCollections);
    }

    protected final void addBinding(Class source, Collection<?> dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (mutableBindings.containsKey(source)) {
            Binding binding = mutableBindings.get(source);
            mutableCollections.put(binding, dest);
        } else {
            Binding binding = new Binding(Scope.COLLECTION);
            mutableBindings.put(source, binding);
            mutableCollections.put(binding, dest);
        }
    }

    protected final void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        if (mutableBindings.containsKey(source)) {
            Binding binding = mutableBindings.get(source);
            binding.setDependencyClass(dest);
        } else {
            Binding binding = new Binding();
            binding.setDependencyClass(dest);
            mutableBindings.put(source, binding);
        }
    }

    protected final void addBindingWithName(Class source, Class dest, String name) {
    }

    final void addSingletonAnnotationIfExists(Class source) {
        if (source.isAnnotationPresent(Singleton.class)) {
            if (bindings.get(source) == null) {
                this.mutableBindings.put(source, new Binding(Scope.SINGLETON));
                this.bindings = ImmutableMap.copyOf(mutableBindings);
            } else {
                this.bindings.get(source).setSingleton();
            }
        }
    }

    final boolean containsBindingToOtherClass(Class source) {
        return getBindings().containsKey(source) && getBindings().get(source).getDependencyClass() != null;
    }

    final boolean containsSingletonBinding(Class source) {
        return getBindings().get(source) != null && getBindings().get(source).getScope() == Scope.SINGLETON;
    }

    final boolean containsCollectionBinding(Class source) {
        return getBindings().get(source) != null && getBindings().get(source).getScope() == Scope.COLLECTION;
    }

    public abstract void configure();

}
