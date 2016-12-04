package examples.twolevelinheritanceconstructorinjection;

import framework.Inject;

class DatabaseTransactionLog extends TransactionLog {

    @Inject()
    DatabaseTransactionLog(final DatabaseTransactionLog transactionLog) {
        super(transactionLog);
    }
}
