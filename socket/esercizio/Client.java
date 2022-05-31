import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException {

		System.out.println(InetAddress.getLocalHost().getHostName());
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), 2000);
			System.out.println("Connessione sulla porta " + 2000);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			out.write("CLIENT MESSAGE", 0, "CLIENT MESSAGE".length());

			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			System.out.println("Host sconosciuto");
		} catch (IOException e) {
			System.out.println("Porta " + 2000 + " non disponibile");
		}

	}
}