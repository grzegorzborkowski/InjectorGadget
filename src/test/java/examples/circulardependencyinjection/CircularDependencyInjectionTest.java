package examples.circulardependencyinjection;

import framework.AbstractInjectService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class CircularDependencyInjectionTest {
    private Account account;

    @Before
    public void setUp() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        AbstractInjectService injectService = new AccountService();
        this.account = injectService.getObjectInstance(Account.class);
    }

    @Test
    public void circularDITestAccount() throws Exception {
        assertThat(account.getOwner(), instanceOf(Person.class));
    }

    @Test
    public void circularDITestPerson() throws Exception {
        assertThat(account.getOwner().getAccount(), instanceOf(Account.class));
    }

}
