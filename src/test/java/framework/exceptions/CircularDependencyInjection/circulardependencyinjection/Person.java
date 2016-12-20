package framework.exceptions.CircularDependencyInjection.circulardependencyinjection;

import framework.adnotations.Inject;

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
