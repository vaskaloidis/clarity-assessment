package lib;

import enums.RequestType;
import error.RequestParameterError;
import model.Aircraft;

public class Request {

    protected RequestType requestType;
    protected Aircraft aircraft;


    /**
     * For use when starting the system and dequeueing aircraft
     * @param type
     * @throws Exception
     */
    public Request(RequestType type) {
        if(type.equals(RequestType.ENQUEUE)) {
            throw new RequestParameterError("You must provide an aircraft to enqueue");
        } else {
            this.requestType = type;
        }
    }

    /**
     * For use when enqueueing aircraft
     * @param type
     * @param aircraft
     */
    public Request(RequestType type, Aircraft aircraft) {
        if(type.equals(RequestType.DEQUEUE)) {
            throw new RequestParameterError("You cannot provide an aircraft when attempting to dequeue one");
        } else if(type.equals(RequestType.START)) {
            throw new RequestParameterError("You cannot provide an aircraft when starting the system");
        } else {
            this.requestType = type;
            this.aircraft = aircraft;
        }
    }

    /**
     * Returns the request type
     * @return requestType object
     */
    public RequestType getType() {
        return requestType;
    }

}
