package examples.twolevelinheritanceconstructorinjection;

import framework.AbstractInjectService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TwoLevelInheritanceConstructionInjectionTest {
    @Test
    public void getTransactionLogFromTwoLevelConstructionInjectionTest() throws Exception {
        AbstractInjectService injectService = new TransactionLogService();
        TransactionLog transactionLog = injectService.getObjectInstance(TransactionLog.class);
        assertThat(transactionLog, instanceOf(PostgresDatabaseTransactionLog.class));
    }
}