package framework.annotations;

import framework.BindingContainer;
import framework.InjectService;
import framework.exceptions.AmbigiousConstructorException;
import org.junit.Test;

public class AmbigiousConstructorClassTest {
    @Test(expected = AmbigiousConstructorException.class)
    public void AmbigiousConstructorClassTest() throws Exception {
        InjectService injectService = new InjectService(new BindingContainer());
        injectService.getObjectInstance(AmbigiousConstructorClass.class);
    }
}
