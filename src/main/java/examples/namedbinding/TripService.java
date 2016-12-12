package examples.namedbinding;

import framework.BindingContainer;

class TripService extends BindingContainer {
    @Override
    public void configure() {
        addBindingWithName(Accommodation.class, Hotel.class, "Hotel");
    }
}
