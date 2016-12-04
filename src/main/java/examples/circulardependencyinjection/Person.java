package examples.circulardependencyinjection;

import framework.Inject;

import java.util.List;

/**
 * Created by tomek on 04.12.2016.
 */
class Person {
    private Account account;

    @Inject()
    public Person(Account account){
        setAccount(account);
    }

    public Account getAccount(){
        return this.account;
    }

    public void setAccount(Account accounts){
        this.account = accounts;
    }
}
