package examples.namedbinding;

import framework.AbstractInjectService;

public class NamedBindingInjection {
    static Accomodation getAccomodationFromNamedBindingInjection() {
        AbstractInjectService injectService = new TripService();
        return injectService.getObjectInstance(Trip.class).getAccomodation();
    }
}
