import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Server {

	private Selector selector;
	private ByteBuffer buffer;

	public Server(int port) {
		try {
			ServerSocketChannel server_socket = ServerSocketChannel.open();
			server_socket.socket().bind(new InetSocketAddress(port));
			server_socket.configureBlocking(false);

			selector = Selector.open();
			server_socket.register(selector, SelectionKey.OP_ACCEPT);

			buffer = ByteBuffer.allocate(1024);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void accept(SelectionKey key) {
		try {
			ServerSocketChannel server_socket = (ServerSocketChannel) key.channel();
			SocketChannel socket = server_socket.accept();
			socket.configureBlocking(false);

			socket.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean send(SelectionKey key) {
		try {
			SocketChannel socket = (SocketChannel) key.channel();
			socket.configureBlocking(false);

			buffer.clear();
			String date = new Date().toString();
			buffer.put(date.getBytes());
			buffer.flip();

			while (buffer.hasRemaining())
				socket.write(buffer);

			socket.register(selector, SelectionKey.OP_READ);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void receive(SelectionKey key) {
		try {
			SocketChannel socket = (SocketChannel) key.channel();
			socket.configureBlocking(false);

			buffer.clear();
			socket.read(buffer);
			buffer.flip();

			String s = new String(buffer.array(), 0, buffer.limit());
			System.out.println(s);

			socket.register(selector, SelectionKey.OP_WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		boolean sent = false;
		while (!sent) {
			try {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while (it.hasNext()) {
					SelectionKey k = it.next();
					it.remove();

					if (k.isAcceptable()) {
						this.accept(k);
					} else if (k.isReadable()) {
						this.receive(k);
					} else if (k.isWritable()) {
						sent = this.send(k);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void shutdown() {
		try {
			selector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(1500);
		server.run();
		server.shutdown();
	}
}
