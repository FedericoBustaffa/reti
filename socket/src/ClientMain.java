import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws Exception {

        Socket client = null;
        try {
            client = new Socket(InetAddress.getLocalHost(), 1);
            System.out.println("Service on port " + client.getPort());
        } catch (IOException e) {
            System.out.println("Port " + client + " not available");
        } finally {
            client.close();
        }

    }
}
