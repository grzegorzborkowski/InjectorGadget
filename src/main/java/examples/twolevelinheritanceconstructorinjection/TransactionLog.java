package examples.twolevelinheritanceconstructorinjection;

import framework.annotations.Inject;

public abstract class TransactionLog {
    @Inject()
    public TransactionLog() {
    }
}
