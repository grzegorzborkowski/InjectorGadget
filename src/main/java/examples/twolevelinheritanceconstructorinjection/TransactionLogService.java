package examples.twolevelinheritanceconstructorinjection;

import framework.AbstractInjectService;

class TransactionLogService extends AbstractInjectService {

    @Override
    public void configure() {
        addBinding(TransactionLog.class, DatabaseTransactionLog.class);
        addBinding(DatabaseTransactionLog.class, PostgresDatabaseTransactionLog.class);
    }
}
