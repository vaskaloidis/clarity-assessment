package enums;
import util.Utils;

public enum AircraftSize {

    SMALL, LARGE;


    public String title() {
        return Utils.titleize(this.name());
    }

}