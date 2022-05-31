import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Collections;
import java.util.Enumeration;

public class Main {
	public static void main(String[] args) throws UnknownHostException, SocketException {
		/*
		 * Security.setProperty("networkaddress.cache,ttl", "0"); long start =
		 * System.currentTimeMillis(); for (int i = 0; i < 1000; i++) {
		 * System.out.println(i + " " +
		 * InetAddress.getByName("www.cnn.com").getHostAddress()); } long end =
		 * System.currentTimeMillis(); System.out.println("tempo trascorso: " + (end -
		 * start));
		 */

		/*
		 * Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		 * for (NetworkInterface netint : Collections.list(nets)) {
		 * System.out.println("****"); System.out.println("Display name: " +
		 * netint.getDisplayName()); System.out.println("Name: " + netint.getName()); }
		 */

		InetAddress local = InetAddress.getByName("10.102.0.106");
		NetworkInterface ni = NetworkInterface.getByInetAddress(local);
		if (ni == null) {
			System.err.println("No local Loopback address");
		} else {
			System.out.println(ni);
		}
	}
}