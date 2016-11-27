package examples.twolevelconstructorinjection;

import framework.Inject;

class DatabaseTransactionLog extends TransactionLog {

    @Inject(bindingName = "")
    DatabaseTransactionLog(final DatabaseTransactionLog transactionLog) {
        super(transactionLog);
    }
}
