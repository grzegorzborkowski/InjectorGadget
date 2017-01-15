package examples.collectioninjection;

import framework.BindingContainer;

import java.util.ArrayList;
import java.util.Collection;

public class SchoolService extends BindingContainer {
    private ArrayList<Teacher> teacherArrayList;

    // Fails because configure() in BindingCointainer constructor is called before creating this array
    /*
    public SchoolService() {
        this.teacherArrayList = new ArrayList<>();
        this.teacherArrayList.add(new Teacher("First"));
        this.teacherArrayList.add(new Teacher("Second"));
    }
    */
    @Override
    public void configure() {
        this.teacherArrayList = new ArrayList<>();
        this.teacherArrayList.add(new Teacher("First"));
        this.teacherArrayList.add(new Teacher("Second"));
        addBinding(Collection.class, teacherArrayList);
    }
}
