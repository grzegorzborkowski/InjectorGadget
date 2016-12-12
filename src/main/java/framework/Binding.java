package framework;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Binding {
    @Getter
    private List<Class> classList;

    public Binding() {
        classList = new ArrayList<>();
    }
}
