package examples.twolevelinheritanceconstructorinjection;

import framework.Inject;

public abstract class TransactionLog {
    @Inject()
    public TransactionLog() {
    }
}
