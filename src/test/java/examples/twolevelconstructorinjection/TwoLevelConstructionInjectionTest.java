package examples.twolevelconstructorinjection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TwoLevelConstructionInjectionTest {
    @Test
    public void getTransactionLogFromTwoLevelConstructionInjectionTest() throws Exception {
        TransactionLog transactionLog = TwoLevelConstructionInjection.getTransactionLog();
        assertThat(transactionLog, instanceOf(PostgresDatabaseTransactionLog.class));
    }
}