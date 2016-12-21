package examples.twolevelinheritanceconstructorinjection;

import framework.annotations.Inject;

public class PostgresDatabaseTransactionLog extends DatabaseTransactionLog {

    @Inject()
    public PostgresDatabaseTransactionLog() {
        super();
    }
}
