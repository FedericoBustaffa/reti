import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.Security;
import java.util.Collections;
import java.util.Enumeration;

public class Main {

    private static void caching() throws Exception {
        Security.setProperty("networkaddress.cache.ttl", "100000");
        long start = System.currentTimeMillis();
        InetAddress add;
        for (int i = 0; i < 1000; i++) {
            add = InetAddress.getByName("www.google.com");
            System.out.println(add.getHostAddress());
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        System.out.println(diff + " ms");
    }

    private static void network_interfaces() throws Exception {
        NetworkInterface net_int = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        System.out.println("LocalHost Network Interface: " + net_int.getName());

        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface ni : Collections.list(nets)) {
            System.out.println("*****");
            System.out.println("Display name: " + ni.getDisplayName());
            System.out.println("Name: " + ni.getName());
            Enumeration<InetAddress> inet_add = ni.getInetAddresses();
            for (InetAddress ia : Collections.list(inet_add)) {
                System.out.println("InetAddress: " + ia);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        caching();
        network_interfaces();
    }
}
