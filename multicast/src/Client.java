import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            int port = 4000;
            List<NetworkInterface> net_ifs = Collections.list(NetworkInterface.getNetworkInterfaces());
            List<InetAddress> addresses;
            for (NetworkInterface ni : net_ifs) {
                System.out.println("*****");
                System.out.println("Interface: " + ni.getName());
                addresses = Collections.list(ni.getInetAddresses());
                for (InetAddress addr : addresses) {
                    System.out.printf("Address: %s [%s]\n", addr.getHostAddress(),
                            addr.isMulticastAddress() ? "multicast" : "no multicast");
                }
            }

            MulticastSocket ms = new MulticastSocket(port);
            SocketAddress group = new InetSocketAddress("239.255.1.3", port);
            ms.joinGroup(group, NetworkInterface.getByName("lo"));
            ms.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
