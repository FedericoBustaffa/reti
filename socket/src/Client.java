import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {

	private String name;
	private Socket socket;
	private int port;
	private BufferedWriter writer;

	public Client(String name) throws NullPointerException {
		this.name = name;

		int i = 1023;
		boolean bound = false;
		while (i < 5000 && !bound) {
			try {
				socket = new Socket(InetAddress.getByName("192.168.1.21"), i);
				bound = true;
				port = i;

				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				writer.write(name + "\n");
				writer.flush();
			} catch (ConnectException e) {
				System.out.println("Servizio sulla porta " + i + " non disponibile");
				i++;
			} catch (SocketException e) {
				System.out.println("Errore del socket");
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
			writer.write(msg + "\n");
			writer.flush();
		} catch (IOException e) {
			System.out.println("Errore in scrittura");
		}
	}

	public void close() {
		try {
			writer.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Errore in chiusura");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Nome utente: ");
		Client client = new Client(in.nextLine());
		System.out.println("Connesso sulla porta " + client.getPort());

		String msg;
		do {
			System.out.print("Scrivi: ");
			msg = in.nextLine();
			client.write(msg);
		} while (!msg.equals("FINE"));

		in.close();
		client.close();
	}
}
