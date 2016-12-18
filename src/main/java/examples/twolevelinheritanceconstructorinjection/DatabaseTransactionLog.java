package examples.twolevelinheritanceconstructorinjection;

import framework.Inject;

public class DatabaseTransactionLog extends TransactionLog {

    @Inject()
    public DatabaseTransactionLog() {
        super();
    }
}
