package enums;

import util.Utils;

public enum AircraftType {
    PASSENGER, CARGO;

    public String title() {
        return Utils.titleize(this.name());
    }
}