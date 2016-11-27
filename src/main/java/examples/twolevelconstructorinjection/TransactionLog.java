package examples.twolevelconstructorinjection;

public abstract class TransactionLog {
    TransactionLog transactionLog;

    TransactionLog(final TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }
}
