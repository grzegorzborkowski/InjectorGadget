package examples.namedbinding;

import framework.Inject;
import lombok.Getter;

class Trip {
    @Getter
    private Accommodation accommodation;

    @Inject(bindingName = "Hotel")
    public Trip(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
