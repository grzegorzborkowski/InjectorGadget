package examples.namedbinding;

import framework.AbstractInjectService;

class TripService extends AbstractInjectService {

    @Override
    public void configure() {
        addBindingWithName(Accomodation.class, Hotel.class, "Hotel");
    }
}
