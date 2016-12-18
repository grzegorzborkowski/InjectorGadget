package examples.twolevelinheritanceconstructorinjection;

import framework.Inject;

public class PostgresDatabaseTransactionLog extends DatabaseTransactionLog {

    @Inject()
    public PostgresDatabaseTransactionLog() {
        super();
    }
}
