package lib;

import model.Aircraft;

import java.util.ArrayList;

public class Response {

    private ArrayList<Aircraft> queue;
    private Aircraft aircraft;
    private Request request;
    private String message;

    /**
     * Create a new response object for the ACM_request_process
     * @param message a message about the requested action
     * @param aircraft the aircraft (if any) being queued or dequeued
     * @param queue the aircraft queue
     * @param request the request itself
     */
    public Response(String message, Aircraft aircraft, ArrayList<Aircraft> queue, Request request) {
        this(message, request);
        this.aircraft = aircraft;
        this.queue = queue;
    }

    /**
     * A simple response to a startup
     * @param message a message about the requested action
     * @param request the startup request
     */
    public Response(String message, Request request) {
        this.message = message;
        this.request = request;
    }

    public ArrayList<Aircraft> getQueue() {
        return queue;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Request getRequest() {
        return request;
    }

    public String getMessage() { return message; }

}
