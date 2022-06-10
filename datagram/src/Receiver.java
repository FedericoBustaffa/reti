import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receiver {

	private int port;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private int buffer_size;

	public Receiver(int port) {
		try {
			socket = new DatagramSocket(port);
			this.port = port;
			socket.setSoTimeout(20000);
			packet = new DatagramPacket(new byte[1024], 1024);
			buffer_size = socket.getReceiveBufferSize();
		} catch (SocketException e) {
			if (socket == null) {
				System.out.println("Porta " + port + " occupata");
				this.port = -1;
			} else {
				System.out.println("Timeout");
			}
		}
	}

	public int getPort() {
		return port;
	}

	public int getBufferSize() {
		return buffer_size;
	}

	public void receive() throws IOException {
		socket.receive(packet);
	}

	public String get() throws UnsupportedEncodingException {
		return new String(packet.getData(), 0, packet.getLength(), "UTF-8");
	}

	public InetAddress getSenderAddress() {
		return packet.getAddress();
	}

	public int getSenderPort() {
		return packet.getPort();
	}

	public void close() {
		socket.close();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		Receiver receiver = new Receiver(1500);

		if (receiver.getPort() != -1) {
			System.out.println("Receiver attivo sulla porta " + receiver.getPort());
			System.out.println("Dimensione receive_buffer: " + receiver.getBufferSize() / 1024 + " KB");
		} else {
			System.out.println("Avvio receiver fallito");
			return;
		}

		try {
			receiver.receive();
			String msg = receiver.get();
			System.out.printf("%s si e' connesso\nIP: %s\nPorta: %d\n", msg,
					receiver.getSenderAddress().getHostAddress(), receiver.getPort());
			do {
				receiver.receive();
				msg = receiver.get();
				if (msg.equals("FINE")) {
					System.out.println("Client disconnesso");
				} else {
					System.out.println("Messaggio ricevuto: " + msg);
				}
			} while (!msg.equals("FINE"));
		} catch (IOException e) {
			System.out.println("Tempo scaduto");
		}
		receiver.close();
	}
}
