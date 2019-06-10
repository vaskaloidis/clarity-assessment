package util;

import enums.AircraftSize;
import enums.AircraftType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    public static Logger logger() {
        return LoggerFactory.getLogger(Utils.class);
    }

    public static Logger logger(Class className) {
        return LoggerFactory.getLogger(className);
    }

    public static void clearScreenAlternate() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").startsWith("Window"))
                Runtime.getRuntime().exec("cls");
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch(Exception e) {
            logger().error("Error clearing screen");
        }
    }

    public static AircraftSize parseAircraftSize(String input) {
        if (input.equalsIgnoreCase("large")) {
            return AircraftSize.LARGE;
        } else {
            return AircraftSize.SMALL;
        }
    }

    public static AircraftType parseAircraftType(String type) {
        if (type.equalsIgnoreCase("passenger")) {
            return AircraftType.PASSENGER;
        } else {
            return AircraftType.CARGO;
        }
    }

    public static String titleize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }


    public static void printGreen(String text) {
        p(GREEN + text + Utils.RESET);
    }

    public static void printRed(String text) {
        p(RED + text + Utils.RESET);
    }

    public static void printCyan(String text) {
        p(CYAN + text + Utils.RESET);
    }

    public static void printGreenLn(String text) {
        pln(GREEN + text + Utils.RESET);
    }

    public static void printRedLn(String text) {
        pln(RED + text + Utils.RESET);
    }

    public static void printCyanLn(String text) {
        pln(CYAN + text + Utils.RESET);
    }

    public static void p(String text) {
        System.out.print(text);
    }

    public static void pln(String text) {
        System.out.println(text);
    }

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE


}
