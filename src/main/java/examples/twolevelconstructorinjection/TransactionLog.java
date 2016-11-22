package examples.twolevelconstructorinjection;

public abstract class TransactionLog {
    TransactionLog transactionLog;

    public TransactionLog(final TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }
}
