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

        // 1. Passenger AC > Cargo AC
        // 2. Large AC > Small AC (Same Type)
        // 3. Earlier enqueued AC > Later Enqueued (Same Type + Size)

//        LocalDate firstDate = first.getQueueTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate secondDate = first.getQueueTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        if(first.getType().equals(second.getType())){
            if(first.getSize().equals(second.getSize())) {
//                if(firstDate.isBefore(secondDate)) {
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

//        if(!first.getType().equals(second.getType()) && first.getType().equals(AircraftType.PASSENGER)) {
//            return 3;
//        } else if(first.getSize().equals(second.getSize()) && first.getSize().equals(AircraftSize.LARGE)) {
//            return 2;
//        } else if(((first.getType().equals(second.getType())) && (first.getSize().equals(second.getSize()))) && firstDate.isAfter(secondDate)) {
//            return 1;
//        } else {
//            return -1;
//        }
    }

}
