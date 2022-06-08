import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) throws IOException {
		ServerSocket server_socket = null;
		for (int i = 1; i < 2048; i++) {
			try {
				server_socket = new ServerSocket(i);
				if (server_socket.isBound()) {
					System.out.println("Listening on port " + server_socket.getLocalPort());
					break;
				}
			} catch (BindException e) {
				System.out.println("Port " + i + " not available");
			}
		}

		if (server_socket != null) {
			try (Socket connection = server_socket.accept()) {
				System.out.println("Client connected");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		server_socket.close();

	}
}
