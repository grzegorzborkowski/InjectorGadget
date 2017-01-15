package framework;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import framework.exceptions.CircularDependencyException;

import java.util.Map;

public class CycleDetector {
    private MutableGraph<Class> graph = GraphBuilder.directed().build();

    public CycleDetector(Map<Class, Binding> bindings) {
        this.addEdgesFromBindingContainer(bindings);
        addEdgesFromBindingContainer(bindings);
    }

    private void addEdgesFromBindingContainer(Map<Class, Binding> bindings) {
        for (Map.Entry<Class, Binding> entry : bindings.entrySet()) {
            if(entry.getValue().getScope() == Scope.PROTOTYPE){
                Class to = entry.getValue().getDependencyClass();
                graph.putEdge(entry.getKey(), to);
            }
        }
    }

    public void addEdge(Class source, Class dest) {
        graph.putEdge(source, dest);
        checkCycle();
    }

    final void checkCycle() {
        boolean hasCycle = Graphs.hasCycle(graph);
        if (hasCycle) {
            throw new CircularDependencyException("Cyclic dependencies are not allowed!");
        }
    }
}