import enums.AircraftSize;
import enums.AircraftType;
import enums.RequestType;
import error.RequestParameterError;
import lib.Request;
import model.Aircraft;
import org.junit.Test;

public class RequestTest {

    @Test(expected = RequestParameterError.class)
    public void testStartRequestParamError() {
        new Request(RequestType.START, new Aircraft(AircraftType.CARGO, AircraftSize.LARGE));
    }

    @Test(expected = RequestParameterError.class)
    public void testDequeueRequestParamError() {
        new Request(RequestType.DEQUEUE, new Aircraft(AircraftType.CARGO, AircraftSize.LARGE));
    }

    @Test(expected = RequestParameterError.class)
    public void TestEnqueueParamError() {
        new Request(RequestType.ENQUEUE);
    }

}