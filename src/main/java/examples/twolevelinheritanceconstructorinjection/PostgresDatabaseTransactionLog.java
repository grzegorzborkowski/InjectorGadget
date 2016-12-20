package examples.twolevelinheritanceconstructorinjection;

import framework.adnotations.Inject;

public class PostgresDatabaseTransactionLog extends DatabaseTransactionLog {

    @Inject()
    public PostgresDatabaseTransactionLog() {
        super();
    }
}
