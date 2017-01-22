package examples.fieldinjection;

import framework.annotations.Inject;

public class User {
    @Inject
    public OptionalInfo optionalInfo;
    private RequiredInfo requiredInfo;

    @Inject
    public User(RequiredInfo reqInfo){
        this.requiredInfo = reqInfo;
    }
}
