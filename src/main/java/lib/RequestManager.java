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

    public String printQueue() {
        StringBuilder sb = new StringBuilder();
        this.queue.getQueue().forEach( ac -> {
            sb.append(ac);
        });
        return sb.toString();
    }

    public Boolean systemRunning() {
        return this.systemRunning;
    }

    public Response acm_request_process(Request request) {

        if(this.systemRunning) {
            Aircraft ac;
            switch(request.requestType) {
                case START:
                    throw new AlreadyStartedError("System is already started");
                case ENQUEUE:
                    System.out.println("Attempting to enqueue: " + request.aircraft.toString());
                    ac = this.queue.enqueueAircraft(request.aircraft);
                    return new Response("Aircraft enqueued", ac, getQueue(), request);
                case DEQUEUE:
                    ac = this.queue.dequeueAircraft();
                    return new Response("Aircraft dequeued", ac, getQueue(), request);
            }
        } else if(request.requestType.equals(RequestType.START)) {
            this.systemRunning = true;
            return new Response("System started", request);
        } else {
            throw new SystemNotStartedError("System is not started yet");
        }
        return new Response("Error", request);
    }


}