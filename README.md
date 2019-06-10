# Clarity ATC System

## Usage

### CLI
The application includes a built-in REST server and an interactive CLI that run in parallel.

Run the CLI `sh build/distributions/ATC-1.0-SNAPSHOT/bin/ATC`  (Batch file available there for windows also).

To build new executables run `gradle build`

To use the system, first select option 3 to start the system

```
1. Enqueue aircraft
2. Dequeue aircraft
** 3. Start System **
4. Exit
5. Refresh
```

Once you start the system, you can create aircraft and enqueue them with option 1

```
Selection:
1
Tail Number:
N787BR
PASSENGER or CARGO Aircraft?
passenger
SMALL or LARGE Aircraft?
small
```

You can view the currently (sorted) enqueued aircraft at the top, along-side the system status:

```
********************************************************
AIR TRAFFIC CONTROL SYSTEM
Visit http://localhost:4567/queue to view REST server
System Running: true
Aircraft Queue:
N737BR
********************************************************
```

To dequeue the next air-craft select option 2.

To exit select option 4.

To update the CLI data displayed (Queue / System-Status if it was updated via the REST server in the background), select option 5.

### REST Server

**While the CLI Console is running**  you can:
 - Start the system at (GET) `http://localhost:4567/start`
 - View system status (GET) `http://localhost:4567/status`
 - View the (sorted) queue (GET) `http://localhost:4567/queue`
 - Dequeu the next aircraft (GET) `http://localhost:4567/dequeue`
 - Enqueue an aircraft (GET) `http://localhost:4567/enqueue/:tailnumber/:type/:size`

Visit `http://localhost:4567/queue` for the queue

```
[ { "tailNumber" : "N787BR", "type" : "PASSENGER", "size" : "SMALL", "queueTime" : 543521064296404 } ]
```

To enqueue an aircraft you must send a GET request to `http://localhost:4567/enqueue/:tailnumber/:type/:size`

```
The type can only be passenger or cargo
http://localhost:4567/enqueue/N737BR/passenger/small
http://localhost:4567/enqueue/N737BR/cargo/small

The size can only be small or large
http://localhost:4567/enqueue/N737BR/passenger/small
http://localhost:4567/enqueue/N737BR/passenger/large

You can use any tail-number
http://localhost:4567/enqueue/:tailnumber/passenger/small
```

## Development

The system handles 3 types of requests which are mainly distinguished by the first value of the constructor

```java
    //public class Request {}
    
    // Start Request
    new Request(RequestType.START);

    // Enqueue Aircraft Request
    Aircraft aircraft = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
    new Request(RequestType.ENQUEUE, aircraft);

    // Dequeue Request
    new Request(RequestType.DEQUEUE);
```

To create an aircraft, you must (at minimum) define the aircraft-type (`CARGO` or `PASSENGER`) and aircraft-size (`LARGE` or `SMALL`)


```java
    // Passenger Aircrafts
    Aircraft ac1 = new Aircraft(AircraftType.CARGO, AircraftSize.LARGE);
    Aircraft ac2 = new Aircraft(AircraftType.CARGO, AircraftSize.SMALL);

    // Cargo Aircrafts
    Aircraft ac3 = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
    Aircraft ac4 = new Aircraft(AircraftType.PASSENGER, AircraftSize.SMALL);

    // Additionally you may supply a tail-number
    Aircraft ac5 = new Aircraft("N787BR", AircraftType.PASSENGER, AircraftSize.SMALL);
    Aircraft ac6 = new Aircraft("N595AZ", AircraftType.PASSENGER, AircraftSize.LARGE);
    Aircraft ac6 = new Aircraft("N7225RU", AircraftType.CARGO, AircraftSize.LARGE);
```

To use the system, you must pass-in newly constructed `Request`(s) to `RequestManager#acm_request_process(Request)`

```java
    RequestManager rm = new RequestManager();

    // First start the system (always)
    Response response = rm.acm_request_process(new Request(RequestType.START));

    // Enqueue an aircraft
    Aircraft aircraft = new Aircraft(AircraftType.PASSENGER, AircraftSize.LARGE);
    Request request = new Request(RequestType.ENQUEUE, aircraft);
    Response response = rm.acm_request_process(request);

    // Dequeue the next aircraft
    Request request = new Request(RequestType.DEQUEUE);
    Response response = rm.acm_request_process(request);

    // Get the queue
    ArrayList<Aircraft> queue = rcm.getQueue();

    // Print the queue
    String queue = rcm.printQueue();

    // System status
    Boolean status = rcm.systemRunning();
```

The `acm_request_process()` function returns a Response object with the following data

```
public class Response {
    getQueue()      // The queue after the action was executed
    getAircraft()   // The aircraft that was enqueued or the aircraft dequeued
    getRequest()    // The initial request
    getMessage()    // A message about the action that was executed
}
```

This data is automatically converted to JSON and returned via REST


### Queue algorithm

The comparator in `/main/java/comparator/AircraftComparator.java` follows the following algorithm:

```
1. Passenger AC > Cargo AC
2. Large AC > Small AC (Same Type)
3. Earlier enqueued AC > Later Enqueued (Same Type + Size)
```

**The tests for this algorithm can be found in** `/test/java/QueueTest.java`

## Build and Test

To build and generate distributions:

```
./gradlew build
```

To run tests (SDK should have nearly full test coverage):

```
./gradle test
```