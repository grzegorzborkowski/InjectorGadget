package examples.circulardependencyinjection;

import framework.Inject;

/**
 * Created by tomek on 04.12.2016.
 */
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
