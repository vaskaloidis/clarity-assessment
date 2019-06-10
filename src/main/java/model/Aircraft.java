package model;

import enums.AircraftSize;
import enums.AircraftType;

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

    public String getTailNumber() {
        return tailNumber;
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
    private AircraftType type;
    private AircraftSize size;
    private Long queueTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(tailNumber != null) {
            sb.append(tailNumber + " ");
        }
        sb.append(size.title() + " " + type.title() + " Aircraft");
        return sb.toString();
    }

}
