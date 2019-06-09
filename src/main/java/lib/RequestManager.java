package lib;

import enums.RequestType;
import error.SystemNotStartedError;
import model.Aircraft;
import error.AlreadyStartedError;
import java.util.ArrayList;

public class RequestManager {

    boolean systemRunning = false;
    private Queue queue;

    public RequestManager() {
        queue = new Queue();
    }

    public ArrayList<Aircraft> getQueue() {
        return this.queue.getQueue();
    }

    public Boolean systemRunning() {
        return this.systemRunning;
    }

    public void acm_request_process(Request request) {

        if(this.systemRunning) {
            switch(request.requestType) {
                case START:
                    throw new AlreadyStartedError("System is already started");
                case ENQUEUE:
                    this.queue.enqueueAircraft(request.aircraft);
                    System.out.println("Aircraft enqueued");
                    break;
                case DEQUEUE:
                    Aircraft ac = this.queue.dequeueAircraft();
                    System.out.println("Aircraft Dequeued: " + ac);
                    break;
            }
        } else if(request.requestType.equals(RequestType.START)) {
            this.systemRunning = true;
        } else {
            throw new SystemNotStartedError("System is not started yet");
        }
    }


}