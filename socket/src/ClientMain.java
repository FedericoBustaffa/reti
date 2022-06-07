import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        Socket clientSocket = new Socket();
        OutputStream out = clientSocket.getOutputStream();
        InputStream in = clientSocket.getInputStream();

        out.write("Ciao server".getBytes());
        clientSocket.close();
    }
}
