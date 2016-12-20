package framework;

import lombok.Getter;
import lombok.Setter;

public class Binding {
    @Getter
    @Setter
    private Class dependencyClass;
    @Getter
    private Scope scope;
    /**
     * Returns this instance if scope is singleton and
     * a object is already created.
     */
    @Getter
    @Setter
    private Object instance;

    Binding() {
        this(Scope.PROTOTYPE);
    }

    Binding(Scope scope) {
        dependencyClass = null;
        this.scope = Scope.SINGLETON;
        this.instance = null;
    }

    void setSingleton() {
        this.scope = Scope.SINGLETON;
    }
}
