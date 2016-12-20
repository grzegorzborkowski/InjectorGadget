package framework.exceptions.CircularDependencyInjection;

import framework.InjectService;
import framework.exceptions.CircularDependencyException;
import framework.exceptions.CircularDependencyInjection.circulardependencyinjection.Account;
import framework.exceptions.CircularDependencyInjection.circulardependencyinjection.AccountService;
import org.junit.Test;

public class CircularDependencyInjectionTest {

    @Test(expected = CircularDependencyException.class)
    public void circularDITestAccount() throws Exception {
        InjectService injectService = new InjectService(new AccountService());
        injectService.getObjectInstance(Account.class);
    }

}
