import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import enums.AircraftSize;
import enums.AircraftType;
import enums.RequestType;
import error.AlreadyStartedError;
import error.SystemNotStartedError;
import lib.Request;
import lib.Response;
import lib.RequestManager;
import model.Aircraft;
import org.junit.Test;

public class RequestManagerTest {

    @Test(expected = AlreadyStartedError.class)
    public void testStartAlreadyStartedSystem() {
        RequestManager rm = new RequestManager();
        rm.acm_request_process(new Request(RequestType.START));
        rm.acm_request_process(new Request(RequestType.START));
    }

    @Test(expected = SystemNotStartedError.class)
    public void testEnqueueBeforeStartedProperly() {
        RequestManager rm = new RequestManager();
        rm.acm_request_process(new Request(RequestType.ENQUEUE, new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE)));
    }

    @Test
    public void verifySystemStartedProperly() {
        RequestManager rm = new RequestManager();
        rm.acm_request_process(new Request(RequestType.START));
        assertTrue(rm.systemRunning());
    }

    @Test
    public void verifySystemOffByDefault() {
        RequestManager rm = new RequestManager();
        assertFalse(rm.systemRunning());
    }

    @Test
    public void testEnqueueRequest() {
        Aircraft target = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        RequestManager rm = new RequestManager();
        Request startRequest = new Request(RequestType.START);
        rm.acm_request_process(startRequest);
        Request enqueueRequest = new Request(RequestType.ENQUEUE, target);
        Response response = rm.acm_request_process(enqueueRequest);
        assertTrue(rm.getQueue().contains(target));
        assertEquals(target, response.getAircraft());
        assertEquals(enqueueRequest, response.getRequest());
    }

    @Test
    public void testDoubleEnqueueRequest() {
        Aircraft target1 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft target2 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        RequestManager rm = new RequestManager();
        rm.acm_request_process(new Request(RequestType.START));
        rm.acm_request_process(new Request(RequestType.ENQUEUE, target1));
        rm.acm_request_process(new Request(RequestType.ENQUEUE, target2));
        assertTrue(rm.getQueue().contains(target1));
        assertTrue(rm.getQueue().contains(target2));
    }

    @Test
    public void testDequeue() {
        Aircraft target1 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft target2 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        RequestManager rm = new RequestManager();
        rm.acm_request_process(new Request(RequestType.START));
        rm.acm_request_process(new Request(RequestType.ENQUEUE, target1));
        rm.acm_request_process(new Request(RequestType.ENQUEUE, target2));
        rm.acm_request_process(new Request(RequestType.DEQUEUE));
        assertFalse(rm.getQueue().contains(target1));
        assertTrue(rm.getQueue().contains(target2));
        assertTrue(rm.getQueue().size()==1);
    }
}
