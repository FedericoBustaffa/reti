import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {

        Socket client = null;
        for (int i = 1; i <= 2048; i++) {
            try {
                client = new Socket(InetAddress.getLocalHost(), i);
                System.out.println("Server at port: " + i);
                break;
            } catch (ConnectException e) {
                System.out.println("Port " + i + " occupied");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            client.close();
        } catch (NullPointerException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
