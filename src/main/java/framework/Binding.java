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
     * Returns this instance if scope is singleton or collection and
     * a object is already created.
     */
    @Getter
    @Setter
    private Object instance;

    Binding() {
        this(Scope.PROTOTYPE);
    }

    Binding(Scope scope) {
        switch(scope){
            case SINGLETON:
                dependencyClass = null;
                this.scope = Scope.SINGLETON;
                this.instance = null;
                break;
            case COLLECTION:
                dependencyClass = null;
                this.scope = Scope.COLLECTION;
                this.instance = null;
                break;
        }
    }

    void setSingleton() {
        this.scope = Scope.SINGLETON;
    }

}
