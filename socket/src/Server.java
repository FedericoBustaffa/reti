import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.BindException;

public class Server {

	private String name;
	private int size;
	private ServerSocket server_socket;
	private int port;
	private ExecutorService pool;

	public Server(String name, int size) {
		this.name = name;
		this.size = size;
		boolean bound = false;
		int i = 1;
		while (i < 5000 && !bound) {
			try {
				server_socket = new ServerSocket(i);
				bound = true;
				port = i;
			} catch (BindException e) {
				System.out.println("Porta " + i + " occupata");
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.pool = Executors.newCachedThreadPool();
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getPort() {
		return port;
	}

	public void listen() {
		for (int i = 0; i < size; i++) {
			pool.execute(new Handler(server_socket));
		}
	}

	public void shutdown() {
		pool.shutdown();
		while (!pool.isTerminated())
			;
		try {
			server_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Numero massimo utenti: ");
		Server server = new Server("Chat", in.nextInt());
		in.close();

		System.out.println("Server attivo sulla porta: " + server.getPort());
		server.listen();
		server.shutdown();

	}
}
