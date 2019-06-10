package model;

import enums.AircraftSize;
import enums.AircraftType;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Aircraft
{

    /**
     * Create a new aircraft with only a type and size
     * @param type
     * @param size
     */
    public Aircraft(AircraftType type, AircraftSize size) {
        this.type = type;
        this.size = size;
    }

    /**
     * Create a new aircraft specifying it's tail-number
     * @param tailNumber
     * @param type
     * @param size
     */
    public Aircraft(String tailNumber, AircraftType type, AircraftSize size) {
        this.tailNumber = tailNumber;
        this.type = type;
        this.size = size;
    }

    /**
     * Create a new aircraft specifying it's tail-number and model
     * @param tailNumber
     * @param model
     * @param type
     * @param size
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(tailNumber != null && model != null) {
            sb.append(tailNumber + " " + model + " ");
        } else if(tailNumber != null) {
            sb.append(tailNumber + " ");
        }
        sb.append(size.title() + " " + type.title() + " Aircraft");
        return sb.toString();
    }

}
