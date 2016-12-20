package framework;

import lombok.Getter;
import lombok.Setter;

public class Binding {
    @Getter
    @Setter
    private Class dependencyClass;
    @Getter
    private Scope scope;
    @Getter
    @Setter
    private Object instance;

    public Binding() {
        dependencyClass = null;
//        classList = new ArrayList<>();
        this.scope = Scope.PROTOTYPE;
        this.instance = null;
    }

    public Binding(Scope scope) {
        dependencyClass = null;
//        classList = new ArrayList<>();
        this.scope = Scope.SINGLETON;
        this.instance = null;
    }

    public void setSingleton() {
        this.scope = Scope.SINGLETON;
    }

    @Override
    public String toString() {
        return String.format("Binding{classList=%s, scope=%s, instance=%s}", dependencyClass, scope, instance);
    }
}
