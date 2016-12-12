package framework;

import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

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
}
