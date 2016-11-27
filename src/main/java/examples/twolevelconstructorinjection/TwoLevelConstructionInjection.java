package examples.twolevelconstructorinjection;

import framework.AbstractInjectService;

class TwoLevelConstructionInjection {
    static TransactionLog getTransactionLog() {
        AbstractInjectService injectService = new TransactionLogService();
        return injectService.getObjectInstance(TransactionLog.class);
    }
}
