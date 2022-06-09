import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {

	private DatagramSocket socket;
	private DatagramPacket packet;
	private int port;

	public Server(int port) {
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println("Errore socket");
		}
	}

	public int getPort() {
		return port;
	}

	public void receive() {
		try {
			packet = new DatagramPacket(new byte[1024], 1024);
			socket.receive(packet);
		} catch (IOException e) {
			System.out.println("Errore ricezione messaggio");
		}
	}

	public String get() throws UnsupportedEncodingException {
		return new String(packet.getData(), 0, packet.getLength(), "US-ASCII");
	}

	public void close() {
		socket.close();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Server server = new Server(1500);
		System.out.println("Server attivo sulla porta " + server.getPort());

		String msg;
		do {
			server.receive();
			msg = server.get();
			if (msg.equals("FINE")) {
				System.out.println("Client disconnesso");
			} else {
				System.out.println("Messaggio ricevuto: " + msg);
			}
		} while (!msg.equals("FINE"));

		server.close();
	}
}
