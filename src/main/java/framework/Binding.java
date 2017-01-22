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
    private String name;

    Binding() {
        this(Scope.PROTOTYPE);
    }

    Binding(Scope scope) {
        switch(scope){
            case SINGLETON:
                dependencyClass = null;
                name = null;
                this.scope = Scope.SINGLETON;
                break;
            case COLLECTION:
                dependencyClass = null;
                name = null;
                this.scope = Scope.COLLECTION;
                break;
            case PROTOTYPE:
                dependencyClass = null;
                name = null;
                this.scope = Scope.PROTOTYPE;
                break;
        }
    }

    void setSingleton() {
        this.scope = Scope.SINGLETON;
    }

}
