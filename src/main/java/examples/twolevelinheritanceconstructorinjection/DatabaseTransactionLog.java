package examples.twolevelinheritanceconstructorinjection;

import framework.adnotations.Inject;

public class DatabaseTransactionLog extends TransactionLog {

    @Inject()
    public DatabaseTransactionLog() {
        super();
    }
}
