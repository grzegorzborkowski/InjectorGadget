package examples.collectioninjection;

import framework.BindingContainer;
import framework.InjectService;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class CollectionInjectionTest {

    @Test
    public void testCollectionInjection() throws Exception {
        BindingContainer bindingContainer = new SchoolService();
        InjectService injectService = new InjectService(bindingContainer);
        School school = injectService.getObjectInstance(School.class);
        assertThat(school.getTeacherCollection(), instanceOf(ArrayList.class));
    }
}
