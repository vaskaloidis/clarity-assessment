package lib;

import comparator.AircraftComparator;
import error.EmptyQueueError;
import model.Aircraft;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Queue {

    private ArrayList<Aircraft> queue;

    public Queue(ArrayList<Aircraft> aircrafts) {
        this.queue = aircrafts;
        sort();
    }

    public Queue() {
        this.queue = new ArrayList<>();
    }

    public Aircraft enqueueAircraft(Aircraft ac) {
        ac.setQueueTime();
        this.queue.add(ac);
        sort();
        return ac;
    }

    public Aircraft dequeueAircraft() {
        if(this.queue.size()==0) {
            throw new EmptyQueueError("There is nothing in the queue");
        } else {
            return this.queue.remove(this.queue.size() - 1);
        }
    }

    public ArrayList<Aircraft> getQueue() {
        return this.queue;
    }

    private void sort() {
        Collections.sort(this.queue, new AircraftComparator());
    }

}