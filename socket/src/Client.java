import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	private String name;
	private Socket socket;
	private BufferedWriter writer;

	public Client(String name) throws NullPointerException {
		this.name = name;
		socket = new Socket();
	}

	public void connect() {
		try {
			InetAddress add = InetAddress.getByName("scheggia");
			System.out.println(add.getHostAddress());
			socket.connect(new InetSocketAddress(add, 1500));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			writer.write(name + "\n");
			writer.flush();
		} catch (ConnectException e) {
			System.out.println("Connection error");
		} catch (SocketException e) {
			System.out.println("Socket error");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void write(String msg) {
		try {
			writer.write(msg + "\n");
			writer.flush();
		} catch (IOException e) {
			System.out.println("Writing error");
		}
	}

	public void close() {
		try {
			writer.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error on close");
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Client name: ");
		Client client = new Client(in.nextLine());
		client.connect();
		System.out.println("Service on port 1500");

		String msg;
		do {
			System.out.print("Write to server: ");
			msg = in.nextLine();
			client.write(msg);
		} while (!msg.equals("close"));

		in.close();
		client.close();
	}
}
