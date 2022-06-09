import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            System.out.println(socket.getPort());
            socket.setSoTimeout(15000);
            InetAddress host = InetAddress.getByName("test.rebex.net");

            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, 13);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            socket.send(request);
            socket.receive(response);

            String daytime = new String(response.getData(), 0, response.getLength(), "Us-ASCII");
            System.out.println(daytime);

        } catch (SocketException e) {
            System.out.println("Socket error");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
