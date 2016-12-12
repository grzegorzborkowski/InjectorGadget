package examples.twolevelinheritanceconstructorinjection;

import framework.BindingContainer;

class TransactionBindingContainer extends BindingContainer {

    @Override
    public void configure() {
        addBinding(TransactionLog.class, DatabaseTransactionLog.class);
        addBinding(DatabaseTransactionLog.class, PostgresDatabaseTransactionLog.class);
    }
}
