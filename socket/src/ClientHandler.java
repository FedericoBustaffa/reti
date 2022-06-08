import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private String client_name;
	private Socket socket;
	private BufferedReader reader;

	public ClientHandler(ServerSocket server_socket) {
		try {
			socket = server_socket.accept();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			client_name = reader.readLine();
			System.out.println(client_name + " join the chat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String msg;
		try {
			while ((msg = reader.readLine()) != null) {
				if (!msg.equals("close"))
					System.out.printf("%s: %s\n", client_name, msg);
			}
			reader.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Connection lost");
		} finally {
			System.out.println(client_name + " left the chat");
		}

	}
}
