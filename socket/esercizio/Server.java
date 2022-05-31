import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server_socket = new ServerSocket(2000);
			System.out.println("Servizio creato sulla porta " + 2000);
			Socket socket = server_socket.accept();

			System.out.println("Connessione avvenuta con " + socket.getInetAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message;
			while ((message = in.readLine()) == null)
				;
			System.out.println("Client message: " + message);

			in.close();
			socket.close();
			server_socket.close();
		} catch (BindException e) {
			System.out.println("Porta " + 2000 + " occupata");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
