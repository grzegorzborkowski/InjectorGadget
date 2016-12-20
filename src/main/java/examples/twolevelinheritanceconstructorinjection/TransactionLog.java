package examples.twolevelinheritanceconstructorinjection;

import framework.adnotations.Inject;

public abstract class TransactionLog {
    @Inject()
    public TransactionLog() {
    }
}
