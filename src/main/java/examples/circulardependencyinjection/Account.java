package examples.circulardependencyinjection;

import framework.adnotations.Inject;

class Account {
    public Person owner;

    @Inject()
    public Account(Person owner){
        setOwner(owner);
    }

    public Person getOwner(){
        return this.owner;
    }

    public void setOwner(Person owner){
        this.owner = owner;
    }
}
