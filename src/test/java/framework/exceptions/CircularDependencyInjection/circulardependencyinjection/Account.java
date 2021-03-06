package framework.exceptions.CircularDependencyInjection.circulardependencyinjection;

import framework.annotations.Inject;

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
