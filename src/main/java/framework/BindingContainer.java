package framework;

import com.google.common.collect.ImmutableMap;
import framework.annotations.Singleton;
import lombok.Getter;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class BindingContainer {
    private Map<Class, ArrayList<Binding>> mutableBindings;
    private Map<Binding, Collection> mutableCollections;
    @Getter
    private ImmutableMap<Class, ArrayList<Binding>> bindings;
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

        addArrayListIfNotPresent(source);
        Optional<Binding> oBinding = getCollectionBinding(source);
        if (oBinding.isPresent()) {
            mutableCollections.put(oBinding.get(), dest);
        } else {
            Binding binding = new Binding(Scope.COLLECTION);
            mutableBindings.get(source).add(binding);
            mutableCollections.put(binding, dest);
        }
    }

    protected final void addBinding(Class source, Class dest) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        addArrayListIfNotPresent(source);
        Optional<Binding> oBinding = getUnnamedBindingToOtherClass(source);
        if (oBinding.isPresent()) {
            oBinding.get().setDependencyClass(dest);
        } else {
            Binding binding = new Binding();
            binding.setDependencyClass(dest);
            mutableBindings.get(source).add(binding);
        }
    }

    protected final void addBindingWithName(Class source, Class dest, String name) {
        source = checkNotNull(source);
        dest = checkNotNull(dest);
        addArrayListIfNotPresent(source);
        Optional<Binding> oBinding = getNamedBindingToOtherClass(source, name);
        if (oBinding.isPresent()) {
            oBinding.get().setDependencyClass(dest);
        } else {
            Binding binding = new Binding();
            binding.setDependencyClass(dest);
            binding.setName(name);
            mutableBindings.get(source).add(binding);
        }
    }

    final void addSingletonAnnotationIfExists(Class source) {
        if (source.isAnnotationPresent(Singleton.class)) {
            addArrayListIfNotPresent(source);
            Optional<Binding> oBinding = getSingletonBinding(source);
            if (oBinding.isPresent()) {
                oBinding.get().setSingleton();
            } else {
                this.mutableBindings.get(source).add(new Binding(Scope.SINGLETON));
                this.bindings = ImmutableMap.copyOf(mutableBindings);
            }
        }
    }

    protected final void addArrayListIfNotPresent(Class source){
        if (!mutableBindings.containsKey(source)) {
            mutableBindings.put(source, new ArrayList<>());
        }
    }

    final Optional<Binding> getUnnamedBindingToOtherClass(Class source) {
        if (!mutableBindings.containsKey(source)) {
            return Optional.empty();
        }
        return mutableBindings.get(source).stream()
                .filter(b -> b.getScope() == Scope.PROTOTYPE && b.getName() == null).findFirst();
    }

    final Optional<Binding> getNamedBindingToOtherClass(Class source, String name) {
        if (!mutableBindings.containsKey(source)) {
            return Optional.empty();
        }
        return mutableBindings.get(source).stream()
                .filter(b -> b.getScope() == Scope.PROTOTYPE && b.getName() == name).findFirst();
    }

    final Optional<Binding> getSingletonBinding(Class source){
        if (!mutableBindings.containsKey(source)) {
            return Optional.empty();
        }
        return mutableBindings.get(source).stream()
                .filter(b -> b.getScope() == Scope.SINGLETON).findFirst();
    }

    final Optional<Binding> getCollectionBinding(Class source){
        if (!mutableBindings.containsKey(source)) {
            return Optional.empty();
        }
        return mutableBindings.get(source).stream()
                .filter(b -> b.getScope() == Scope.COLLECTION).findFirst();
    }
    public abstract void configure();

}
