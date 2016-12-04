package examples.twolevelinheritanceconstructorinjection;

import framework.Inject;

class PostgresDatabaseTransactionLog extends DatabaseTransactionLog {

    @Inject()
    public PostgresDatabaseTransactionLog(final DatabaseTransactionLog transactionLog) {
        super(transactionLog);
    }
}
