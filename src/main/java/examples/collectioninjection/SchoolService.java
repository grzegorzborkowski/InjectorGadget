package examples.collectioninjection;

import framework.BindingContainer;

import java.util.ArrayList;
import java.util.Collection;

public class SchoolService extends BindingContainer {
    private ArrayList<Teacher> teacherArrayList;

    public SchoolService() {
        this.teacherArrayList = new ArrayList<>();
        this.teacherArrayList.add(new Teacher("First"));
        this.teacherArrayList.add(new Teacher("Second"));
    }

    @Override
    public void configure() {
        addBinding(Collection.class, teacherArrayList);
    }
}
