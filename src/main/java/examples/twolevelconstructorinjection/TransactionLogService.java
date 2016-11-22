package examples.twolevelconstructorinjection;

import framework.AbstractInjectService;

public class TransactionLogService extends AbstractInjectService {

    @Override
    public void configure() {
        addBinding(TransactionLog.class, DatabaseTransactionLog.class);
        addBinding(DatabaseTransactionLog.class, PostgresDatabaseTransactionLog.class);
    }
}
