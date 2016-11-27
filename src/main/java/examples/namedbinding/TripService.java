package examples.namedbinding;

import framework.AbstractInjectService;

class TripService extends AbstractInjectService {
    @Override
    public void configure() {
        addBindingWithName(Accommodation.class, Hotel.class, "Hotel");
    }
}
