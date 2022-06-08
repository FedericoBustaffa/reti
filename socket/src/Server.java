import java.net.ServerSocket;
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

		int p = 1;
		boolean bounded = false;
		while (!bounded && p < 5000) {
			try {
				server_socket = new ServerSocket(p);
				if (server_socket.isBound()) {
					port = p;
					bounded = true;
				}
			} catch (BindException e) {
				p++;
			} catch (IOException e) {
				System.out.println("IOException occurred");
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

	public void start() {
		for (int i = 0; i < size; i++) {
			pool.execute(new ClientHandler(server_socket));
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
}
