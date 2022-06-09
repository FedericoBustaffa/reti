import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Handler implements Runnable {

	private String client_name;
	private Socket socket;
	private BufferedReader reader;

	public Handler(ServerSocket server_socket) {
		try {
			socket = server_socket.accept();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			client_name = reader.readLine();
			System.out.println(client_name + " si e' unito alla chat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String msg;
		try {
			while ((msg = reader.readLine()) != null) {
				if (!msg.equals("FINE"))
					System.out.printf("%s: %s\n", client_name, msg);
			}
			reader.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Connessione persa");
		} finally {
			System.out.println(client_name + " ha abbandonato la chat");
		}

	}
}
