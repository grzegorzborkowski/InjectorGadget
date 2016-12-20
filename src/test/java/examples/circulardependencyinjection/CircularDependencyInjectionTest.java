package examples.circulardependencyinjection;

import framework.InjectService;
import framework.exceptions.CyclicDependencyException;
import org.junit.Test;

public class CircularDependencyInjectionTest {

    @Test(expected = CyclicDependencyException.class)
    public void circularDITestAccount() throws Exception {
        InjectService injectService = new InjectService(new AccountService());
        injectService.getObjectInstance(Account.class);
    }

}
