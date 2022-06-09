import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws Exception {
        InetAddress local_host = InetAddress.getLocalHost();

        System.out.println("LocalHost ip: " + local_host.getHostAddress());

        for (int i = 1024; i < 5000; i++) {
            try {
                Socket s = new Socket(local_host, i);
                System.out.println("Esiste un servizio sulla porta " + i);
                s.close();
                break;
            } catch (UnknownHostException e) {
                System.out.println("Host sconosciuto");
                break;
            } catch (IOException e) {
                System.out.println("Non esiste un servizio sulla porta " + i);
            }
        }
    }
}
