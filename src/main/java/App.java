import enums.AircraftSize;
import enums.AircraftType;
import lib.RequestManager;
import model.Aircraft;

public class App
{
    public static void main( String[] args )
    {
        Aircraft ac1 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        Aircraft ac3 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac4 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);

    }
}
