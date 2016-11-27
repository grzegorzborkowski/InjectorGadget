package examples.namedbinding;

import framework.AbstractInjectService;

class NamedBindingInjection {
    static Accommodation getAccomodationFromNamedBindingInjection() {
        AbstractInjectService injectService = new TripService();
        return injectService.getObjectInstance(Trip.class).getAccommodation();
    }
}
