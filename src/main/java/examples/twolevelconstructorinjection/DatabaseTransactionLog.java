package examples.twolevelconstructorinjection;

import framework.Inject;

public class DatabaseTransactionLog extends TransactionLog {

    @Inject(bindingName = "")
    public DatabaseTransactionLog(DatabaseTransactionLog transactionLog) {
        super(transactionLog);
    }
}
