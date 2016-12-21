package examples.twolevelinheritanceconstructorinjection;

import framework.annotations.Inject;

public class DatabaseTransactionLog extends TransactionLog {

    @Inject()
    public DatabaseTransactionLog() {
        super();
    }
}
