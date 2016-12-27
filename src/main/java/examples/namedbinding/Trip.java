package examples.namedbinding;

import framework.annotations.Inject;
import lombok.Getter;

public class Trip {
    @Getter
    private Accommodation accommodation;

    @Inject
    public Trip(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
