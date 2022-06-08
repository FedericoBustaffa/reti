import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private String name;
	private Socket client;
	private int port;
	private BufferedWriter out;

	public Client(String name) throws NullPointerException {
		this.name = name;

		boolean bounded = false;
		int p = 1;
		while (!bounded && p < 5000) {
			try {
				client = new Socket(InetAddress.getLocalHost(), p);
				port = p;

				bounded = true;
				out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				out.write(name + "\n");
				out.flush();
			} catch (ConnectException e) {
				p++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public void write(String msg) {
		try {
			out.write(msg + "\n");
			out.flush();
		} catch (IOException e) {
			System.out.println("Writing error");
		}
	}

	public void close() {
		try {
			out.close();
			client.close();
		} catch (IOException e) {
			System.out.println("Error on close");
		}
	}
}
