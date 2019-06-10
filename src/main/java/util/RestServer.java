package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import enums.RequestType;
import lib.Request;
import lib.RequestManager;
import lib.Response;
import model.Aircraft;

import static spark.Spark.get;

public class RestServer {

    public RequestManager rm;

    public RestServer(RequestManager rm) {
        this.rm = rm;
        run();
    }

    public void run() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        get("/queue", (req, res) -> ow.writeValueAsString(rm.getQueue()));
        get("/status", (req, res) -> ow.writeValueAsString(rm.systemRunning()));
        get("/start", (req, res) -> {
            try {
                Response result = rm.acm_request_process(new Request(RequestType.START));
                return ow.writeValueAsString(result);
            } catch (Error error) {
                return ow.writeValueAsString(error.getMessage());
            }
        });

        get("/enqueue/:tailnumber/:type/:size", (req, res) -> {
            try {
                Aircraft aircraft = new Aircraft(req.params(":tailnumber"), Utils.parseAircraftType(req.params(":type")), Utils.parseAircraftSize(req.params(":size")));
                Response result = rm.acm_request_process(new Request(RequestType.ENQUEUE, aircraft));
                return ow.writeValueAsString(result);
            } catch (Error error) {
                return ow.writeValueAsString(error.getMessage());
            }
        });

        get("/dequeue", (req, res) -> {
            try {
                Response result = rm.acm_request_process(new Request(RequestType.DEQUEUE));
                return ow.writeValueAsString(result);
            } catch (Error error) {
                return ow.writeValueAsString(error.getMessage());
            }
        });

    }
}