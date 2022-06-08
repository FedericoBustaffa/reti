import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;

public class Server {

	private ServerSocket server_socket;
	private int port;
	private Socket connection;
	private BufferedReader in;

	public Server() throws NullPointerException {
		int p = 1;
		boolean bounded = false;
		while (!bounded && p < 5000) {
			try {
				server_socket = new ServerSocket(p);
				if (server_socket.isBound()) {
					port = p;
					bounded = true;
				}
			} catch (BindException e) {
				p++;
			} catch (IOException e) {
				System.out.println("IOException occurred");
			}
		}

		if (p == 5000) {
			throw new NullPointerException();
		}

		connection = null;
	}

	public int getPort() {
		return port;
	}

	public void accept() {
		try {
			connection = server_socket.accept();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e) {
			System.out.println("Accept failed");
		}
	}

	public void read() {
		String msg;
		try {
			while ((msg = in.readLine()) != null) {
				System.out.println("Client: " + msg);
			}
		} catch (IOException e) {
			System.out.println("Client has closed the connection");
		}
	}

	public void close() {
		try {
			in.close();
			connection.close();
			server_socket.close();
		} catch (IOException e) {
			System.out.println();
		} catch (NullPointerException e) {

		}
	}
}
