package examples.namedbinding;

import framework.adnotations.Inject;
import lombok.Getter;

class Trip {
    @Getter
    private Accommodation accommodation;

    @Inject(bindingName = "Hotel")
    public Trip(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
