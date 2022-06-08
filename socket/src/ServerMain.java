import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) {

		ServerSocket server_socket = null;
		for (int i = 1; i <= 2048; i++) {
			try {
				server_socket = new ServerSocket(i);
				if (server_socket.isBound()) {
					System.out.println("Listening on port " + server_socket.getLocalPort());
					break;
				}
			} catch (BindException e) {
				System.out.println("Port " + i + " not available");
			} catch (IOException e) {
				System.out.println("IOException occurred");
			}
		}

		Socket connection = null;
		if (server_socket != null) {
			for (int i = 0; i < 2; i++) {
				try {
					connection = server_socket.accept();
					System.out.println("Client connected on port " + connection.getLocalPort());
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			server_socket.close();
		} catch (IOException e) {
			System.out.println("Can't close socket IOException");
		}
	}
}
