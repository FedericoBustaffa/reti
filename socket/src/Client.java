import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {

	private String name;
	private Socket socket;
	private BufferedWriter writer;

	public Client(String name) throws NullPointerException {
		this.name = name;
		socket = new Socket();
	}

	public void connect() {
		try {
			socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write(name + "\n");
			writer.flush();
		} catch (ConnectException e) {
		} catch (SocketException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void write(String msg) {
		try {
			writer.write(msg + "\n");
			writer.flush();
		} catch (IOException e) {
			System.out.println("Writing error");
		}
	}

	public void close() {
		try {
			writer.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error on close");
		}
	}
}
