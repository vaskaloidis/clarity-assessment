import enums.AircraftSize;
import enums.AircraftType;
import error.EmptyQueueError;
import lib.Queue;
import model.Aircraft;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class QueueTest {

    // 1. Passenger AC > Cargo AC
    // 2. Large AC > Small AC (Same Type)
    // 3. Earlier enqueued AC > Later Enqueued (Same Type + Size)

    @Test
    public void testAircraftTypeSort() {
        Aircraft ac1 = new Aircraft("AC1", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft("AC2", AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac3 = new Aircraft("AC3", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac4 = new Aircraft("AC4", AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac5 = new Aircraft("AC5", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac6 = new Aircraft("AC6", AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac7 = new Aircraft("AC7", AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac8 = new Aircraft("AC8", AircraftType.CARGO, AircraftSize.LARGE);

        Queue q = new Queue();
        q.enqueueAircraft(ac1);
        q.enqueueAircraft(ac2);
        q.enqueueAircraft(ac3);
        q.enqueueAircraft(ac4);
        q.enqueueAircraft(ac5);
        q.enqueueAircraft(ac6);
        q.enqueueAircraft(ac7);
        q.enqueueAircraft(ac8);

        ArrayList<Aircraft> result = new ArrayList<>();
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());

        assertTrue(result.contains(ac2));
        assertTrue(result.contains(ac4));
        assertTrue(result.contains(ac6));
        assertTrue(result.contains(ac7));
    }

    @Test
    public void testAircraftSizeSortPassenger() {
        Aircraft ac1 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        Aircraft ac3 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac4 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        Aircraft ac5 = new Aircraft( AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac6 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        Aircraft ac7 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);

        Queue q = new Queue();
        q.enqueueAircraft(ac1);
        q.enqueueAircraft(ac2);
        q.enqueueAircraft(ac3);
        q.enqueueAircraft(ac4);
        q.enqueueAircraft(ac5);
        q.enqueueAircraft(ac6);
        q.enqueueAircraft(ac7);

        ArrayList<Aircraft> result = new ArrayList<>();
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());

        assertTrue(result.contains(ac1));
        assertTrue(result.contains(ac3));
        assertTrue(result.contains(ac5));
        assertTrue(result.contains(ac7));
    }

    @Test
    public void testAircraftSizeSortCargo() {
        Aircraft ac1 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);
        Aircraft ac3 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac4 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);
        Aircraft ac5 = new Aircraft( AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac6 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);
        Aircraft ac7 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);

        Queue q = new Queue();
        q.enqueueAircraft(ac1);
        q.enqueueAircraft(ac2);
        q.enqueueAircraft(ac3);
        q.enqueueAircraft(ac4);
        q.enqueueAircraft(ac5);
        q.enqueueAircraft(ac6);
        q.enqueueAircraft(ac7);

        ArrayList<Aircraft> result = new ArrayList<>();
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());
        result.add(q.dequeueAircraft());

        assertTrue(result.contains(ac1));
        assertTrue(result.contains(ac3));
        assertTrue(result.contains(ac5));
        assertTrue(result.contains(ac7));
    }

    @Test
    public void testAircraftTimeQueue() {
        Aircraft ac1 = new Aircraft("AC1", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft("AC2", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac3 = new Aircraft("AC3", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac4 = new Aircraft("AC4", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac5 = new Aircraft("AC5", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac6 = new Aircraft("AC6", AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac7 = new Aircraft("AC7", AircraftType.CARGO, AircraftSize.LARGE);

        Queue q = new Queue();
        q.enqueueAircraft(ac1);
        q.enqueueAircraft(ac2);
        q.enqueueAircraft(ac3);
        q.enqueueAircraft(ac4);
        q.enqueueAircraft(ac5);
        q.enqueueAircraft(ac6);
        q.enqueueAircraft(ac7);

        ArrayList<Aircraft> expected = new ArrayList<>();
//        Collections.addAll(expected, ac7, ac6, ac5, ac4, ac3, ac2, ac1);
        expected.add(0, ac7);
        expected.add(1, ac6);
        expected.add(2, ac5);
        expected.add(3, ac4);
        expected.add(4, ac3);
        expected.add(5, ac2);
        expected.add(6, ac1);

        assertEquals(expected, q.getQueue());
    }

    @Test
    public void testFullQueueSorter() {
        Aircraft ac1 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac2 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac3 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);
        Aircraft ac4 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);
        Aircraft ac5 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
        Aircraft ac6 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
        Aircraft ac7 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);
        Aircraft ac8 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);

        Queue q = new Queue();
        q.enqueueAircraft(ac1);
        q.enqueueAircraft(ac2);
        q.enqueueAircraft(ac3);
        q.enqueueAircraft(ac4);
        q.enqueueAircraft(ac5);
        q.enqueueAircraft(ac6);
        q.enqueueAircraft(ac7);
        q.enqueueAircraft(ac8);

        ArrayList<Aircraft> expected = new ArrayList<>();
        Collections.addAll(expected, ac7, ac3, ac5, ac1, ac8, ac4, ac6, ac2);

        assertEquals(expected, q.getQueue());
    }

    @Test(expected = EmptyQueueError.class)
    public void TestEmptyQueueError() {
        Queue q = new Queue();
        q.dequeueAircraft();
    }

}