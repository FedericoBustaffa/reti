import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Sender {
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(0);
			byte[] msg = "CIAO".getBytes();
			InetSocketAddress sock_addr = new InetSocketAddress("239.255.1.3", 4000);
			DatagramPacket packet = new DatagramPacket(msg, 0, msg.length, sock_addr);
			socket.send(packet);
			socket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
