import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) throws IOException {
		ServerSocket server_socket = null;
		for (int i = 1; i < 1024; i++) {
			try {
				server_socket = new ServerSocket(i);
				if (server_socket.isBound()) {
					System.out.println("Listening on port " + server_socket.getLocalPort());
					break;
				}
			} catch (IOException e) {
				System.out.println("Port " + i + "not available");
			}
		}

		try (Socket connection = server_socket.accept()) {
			System.out.println("client connesso");
		} catch (IOException e) {
			e.printStackTrace();
		}

		server_socket.close();
	}
}
