package framework;

import lombok.Getter;
import lombok.Setter;

public class Binding {
    @Getter
    @Setter
    private Class dependencyClass;
    @Getter
    private Scope scope;

    Binding() {
        this(Scope.PROTOTYPE);
    }

    Binding(Scope scope) {
        switch(scope){
            case SINGLETON:
                dependencyClass = null;
                this.scope = Scope.SINGLETON;
                break;
            case COLLECTION:
                dependencyClass = null;
                this.scope = Scope.COLLECTION;
                break;
        }
    }

    void setSingleton() {
        this.scope = Scope.SINGLETON;
    }

}
