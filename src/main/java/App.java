import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import enums.RequestType;
import lib.Request;
import lib.RequestManager;
import lib.Response;
import util.InteractiveConsole;
import util.RestServer;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        RequestManager rm = new RequestManager();
        new RestServer(rm);
        new InteractiveConsole(rm);
    }

}
