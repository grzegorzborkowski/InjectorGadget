package examples.twolevelconstructorinjection;

import framework.Inject;

public class DatabaseTransactionLog extends TransactionLog {

    @Inject
    public DatabaseTransactionLog(DatabaseTransactionLog transactionLog) {
        super(transactionLog);
    }
}
