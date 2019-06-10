package util;

import enums.AircraftSize;
import enums.AircraftType;
import enums.RequestType;
import lib.Request;
import lib.RequestManager;
import lib.Response;
import model.Aircraft;

import java.util.Scanner;

public class InteractiveConsole {

    private RequestManager rm;
    private Scanner scanner;
    private Boolean running;

    public InteractiveConsole(RequestManager rm) {
        this.rm = rm;
        this.scanner = new Scanner(System.in);
        this.running = true;
        run();
    }

    public void run() {
        Utils.clearScreenAlternate();
        while (this.running) {
            printStats();
            menu();
        }

    }

    public void printStats() {
        Utils.pln("********************************************************");
        Utils.pln("AIR TRAFFIC CONTROL SYSTEM");
        Utils.pln("Visit http://localhost:4567/queue to view REST server");
        Utils.p("System Running: ");
        if (rm.systemRunning()) {
            Utils.printGreenLn("true");
        } else {
            Utils.printRedLn("false");
        }
        int size = rm.getQueue().size();
        if (size != 0) {
            Utils.pln("Aircraft Queue:");
            for (int x = 0; x <= size - 1; x++) {
                Utils.printGreen(rm.getQueue().get(x).getTailNumber());
                if (x != size - 1) {
                    Utils.printGreen(" -> ");
                }
            }
            Utils.pln("");
        } else {
            Utils.p("Aircraft Queue:");
            Utils.printGreenLn("(None)");
        }
        Utils.pln("********************************************************");
    }

    public void menu() {
        Utils.pln("");
        Utils.printCyanLn("MENU:");
        Utils.printCyanLn("1. Enqueue aircraft");
        Utils.printCyanLn("2. Dequeue aircraft");
        if (!rm.systemRunning()) {
            Utils.printRedLn("** 3. Start System **");
        }
        Utils.printCyanLn("4. Exit");
        Utils.printCyanLn("5. Refresh");
        Utils.pln("");
        Utils.pln("Selection:");
        int selection = scanner.nextInt();

        Request request = null;
        if (selection == 1) {
            request = enqueueAircraft();
        } else if (selection == 2) {
            request = dequeueAircraft();
        } else if (selection == 3) {
            request = startSystem();
        } else if (selection == 4) {
            Utils.printRedLn("Closing...");
            System.exit(0);
        }
        Utils.clearScreenAlternate();
        if (selection != 4 && selection != 5) {
            try {
                Response response = rm.acm_request_process(request);
                Utils.printRedLn(request.getType().equals(RequestType.START) ? response.getMessage() : response.getMessage() + ": " + response.getAircraft());
                Utils.clearScreenAlternate();
            } catch (Error error) {
                Utils.clearScreenAlternate();
                Utils.pln("");
                Utils.printRed("** ERROR: " + error.getMessage() + " **");
                Utils.pln("");
            }
        }
    }

    public Request startSystem() {
        return new Request(RequestType.START);
    }

    public Request dequeueAircraft() {
        return new Request(RequestType.DEQUEUE);
    }

    public Request enqueueAircraft() {
        Utils.pln("Tail Number: ");
        String tailnumber = scanner.next();

        Utils.pln("PASSENGER or CARGO Aircraft? ");
        String type = scanner.next();

        Utils.pln("SMALL or LARGE Aircraft? ");
        String size = scanner.next();

        AircraftType selectedType = Utils.parseAircraftType(type);
        AircraftSize selectedSize = Utils.parseAircraftSize(size);

        Aircraft newAircraft = new Aircraft(tailnumber, selectedType, selectedSize);
        return new Request(RequestType.ENQUEUE, newAircraft);
    }

}
