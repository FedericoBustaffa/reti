import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;

public class Server {

	private String name;
	private int size;
	private ServerSocket server_socket;
	private ExecutorService pool;

	public Server(String name, int size) {
		this.name = name;
		this.size = size;
		try {
			server_socket = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.pool = Executors.newCachedThreadPool();
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public void start() {
		try {
			server_socket.bind(new InetSocketAddress(1500));
		} catch (BindException e) {
			System.out.println("Bind error");
		} catch (IOException e) {
			System.out.println("IOException occurred");
		}
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
		System.out.print("Server size: ");
		Server server = new Server("Chat", in.nextInt());

		server.start();
		System.out.println("Server on port 1500");
		server.listen();
		server.shutdown();

		in.close();
	}
}
