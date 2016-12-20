package examples.parameterinjection;

import framework.adnotations.Inject;

public class User {
    @Inject
    public OptionalInfo optionalInfo;
    private RequiredInfo requiredInfo;

    @Inject
    public User(RequiredInfo reqInfo){
        this.requiredInfo = reqInfo;
    }
}
