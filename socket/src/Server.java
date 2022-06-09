import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server {

	private String name;
	private int size;
	private InetAddress ip;
	private ServerSocket server_socket;
	private ExecutorService pool;

	public Server(String name, int size) {
		this.name = name;
		this.size = size;
		try {
			ip = InetAddress.getLocalHost();
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

	public String getIP() {
		return ip.getHostAddress();
	}

	public void start() {
		try {
			server_socket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
		} catch (BindException e) {
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

		System.out.println("Server IP: " + server.getIP());

		server.start();
		System.out.println("Server on port 1500");
		server.listen();
		server.shutdown();

		in.close();
	}
}
