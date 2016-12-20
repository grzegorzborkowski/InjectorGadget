package examples.circulardependencyinjection;

import framework.adnotations.Inject;

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
