package examples.circulardependencyinjection;

import framework.adnotations.Inject;

public class Account {
    public Person owner;

    @Inject()
    public Account(Person owner){
        this.owner = owner;
    }

    public Person getOwner(){
        return this.owner;
    }

    public void setOwner(Person owner){
        this.owner = owner;
    }
}
