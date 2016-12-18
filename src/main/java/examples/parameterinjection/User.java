package examples.parameterinjection;

import framework.Inject;
import lombok.Getter;

public class User {
    private RequiredInfo requiredInfo;

    @Inject
    public OptionalInfo optionalInfo;

    @Inject
    public User(RequiredInfo reqInfo){
        this.requiredInfo = reqInfo;
    }
}
