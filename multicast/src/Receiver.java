import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class Receiver {
    public static void main(String[] args) {

        int port = 4000;
        try (MulticastSocket ms = new MulticastSocket(port)) {
            InetSocketAddress group = new InetSocketAddress("239.255.1.3", port);
            ms.joinGroup(group, null);

            byte[] buffer = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ms.receive(packet);
            String s = new String(packet.getData(), 0, packet.getLength());
            System.out.println("MSG: " + s);
            ms.leaveGroup(group, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
