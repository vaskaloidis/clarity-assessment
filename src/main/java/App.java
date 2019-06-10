import lib.RequestManager;
import util.InteractiveConsole;
import util.RestServer;

public class App {
    public static void main(String[] args) {
        RequestManager rm = new RequestManager();
        new RestServer(rm);
        new InteractiveConsole(rm);
    }

}
