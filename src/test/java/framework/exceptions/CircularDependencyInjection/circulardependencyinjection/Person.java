package framework.exceptions.CircularDependencyInjection.circulardependencyinjection;

import framework.annotations.Inject;

public class Person {
    public Account account;

    @Inject()
    public Person(Account account){
        this.account = account;
    }

    public Account getAccount(){
        return this.account;
    }

}
