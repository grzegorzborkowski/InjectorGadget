package examples.collectioninjection;

import framework.annotations.Inject;
import lombok.Getter;

import java.util.Collection;

public class School {
    @Getter
    private Collection<Teacher> teacherCollection;

    @Inject
    public School(Collection<Teacher> teacherCollection) {
        this.teacherCollection = teacherCollection;
    }
}
