import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {

        for (int i = 1024; i < 2048; i++) {
            try (Socket client = new Socket(InetAddress.getLocalHost(), i)) {
                System.out.println("Server at port: " + i);
                break;
            } catch (BindException e) {
                System.out.println("Port " + i + " occupied");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
