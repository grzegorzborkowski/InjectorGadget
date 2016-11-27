package examples.namedbinding;

import framework.Inject;

public class Trip {
    Accomodation accomodation;

    @Inject(bindingName = "Hotel")
    public Trip(Accomodation accomodation) {
        this.accomodation = accomodation;
    }

    public Accomodation getAccomodation() {
        return accomodation;
    }
}
