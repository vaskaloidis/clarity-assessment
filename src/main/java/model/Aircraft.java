package model;

import enums.AircraftSize;
import enums.AircraftType;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Aircraft
{

    public Aircraft(AircraftType type, AircraftSize size) {
        this.type = type;
        this.size = size;
    }

    public Aircraft(String tailNumber, AircraftType type, AircraftSize size) {
        this.tailNumber = tailNumber;
        this.type = type;
        this.size = size;
    }

    public Aircraft(String tailNumber, String model, AircraftType type, AircraftSize size) {
        this.tailNumber = tailNumber;
        this.model = model;
        this.type = type;
        this.size = size;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public String getModel() {
        return model;
    }

    public AircraftType getType() {
        return type;
    }

    public AircraftSize getSize() {
        return size;
    }

    public Long getQueueTime() {
        return queueTime;
    }

    public Long setQueueTime() {
        this.queueTime = System.nanoTime();
//        System.out.println("Setting Queue Time: " + getQueueTime());
//        this.queueTime = DateUtils.round(new Date(System.currentTimeMillis()), Calendar.MILLISECOND);
        return this.queueTime;
    }

    private String tailNumber;
    private String model;
    private AircraftType type;
    private AircraftSize size;
    private Long queueTime;
}
