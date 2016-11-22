package examples.twolevelconstructorinjection;

import framework.AbstractInjectService;

public class TwoLevelConstructionInjection {
    static TransactionLog getTransactionLog() {
        AbstractInjectService injectService = new TransactionLogService();
        return injectService.getObjectInstance(TransactionLog.class);
    }
}
