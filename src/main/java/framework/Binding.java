package framework;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Binding {
    @Getter
    private List<Class> classList;
    @Getter
    private Scope scope;
    @Getter
    @Setter
    private Object instance;

    public Binding() {
        classList = new ArrayList<>();
        this.scope = Scope.PROTOTYPE;
        this.instance = null;
    }

    public Binding(Scope scope) {
        classList = new ArrayList<>();
        this.scope = Scope.SINGLETON;
        this.instance = null;
    }

    public void setSingleton() {
        this.scope = Scope.SINGLETON;
    }

    @Override
    public String toString() {
        return "Binding{" +
                "classList=" + classList +
                ", scope=" + scope +
                ", instance=" + instance +
                '}';
    }
}
