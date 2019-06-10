package comparator;

import enums.AircraftSize;
import enums.AircraftType;
import model.Aircraft;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;

public class AircraftComparator implements Comparator<Aircraft> {

    @Override
    public int compare(Aircraft first, Aircraft second) {

        // Algorithm
        // 1. Passenger AC > Cargo AC
        // 2. Large AC > Small AC (Same Type)
        // 3. Earlier enqueued AC > Later Enqueued (Same Type + Size)

        if(first.getType().equals(second.getType())){
            if(first.getSize().equals(second.getSize())) {
                if(first.getQueueTime() < second.getQueueTime()) {
                    return 1;
                } else {
                    return -1;
                }
            } else if(first.getSize().equals(AircraftSize.LARGE)) {
                return 1;
            } else {
                return -1;
            }
        } else if(first.getType().equals(AircraftType.PASSENGER)) {
            return 1;
        } else {
            return -1;
        }
    }

}
