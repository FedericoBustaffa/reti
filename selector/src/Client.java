import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {

    private Selector selector;
    private SocketAddress sock_addr;
    private ByteBuffer buffer;

    public Client(InetAddress addr, int port) {
        try {
            SocketChannel socket = SocketChannel.open();
            socket.configureBlocking(false);

            selector = Selector.open();
            socket.register(selector, SelectionKey.OP_CONNECT);

            sock_addr = new InetSocketAddress(addr, port);
            buffer = ByteBuffer.allocate(1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(SelectionKey key) {
        try {
            SocketChannel socket = (SocketChannel) key.channel();
            socket.configureBlocking(false);
            socket.connect(sock_addr);
            while (!socket.finishConnect())
                ;
            socket.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(SelectionKey key) {
        try {
            SocketChannel socket = (SocketChannel) key.channel();
            socket.configureBlocking(false);

            buffer.clear();
            buffer.put("Orario".getBytes());
            buffer.flip();

            while (buffer.hasRemaining())
                socket.write(buffer);

            socket.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receive(SelectionKey key) {
        try {
            SocketChannel socket = (SocketChannel) key.channel();
            socket.configureBlocking(false);

            buffer.clear();
            socket.read(buffer);
            buffer.flip();

            String date = new String(buffer.array(), 0, buffer.limit());
            System.out.println(date);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void start() {
        boolean received = false;
        while (!received) {
            try {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey k = it.next();
                    it.remove();

                    if (k.isConnectable()) {
                        this.connect(k);
                    } else if (k.isWritable()) {
                        this.send(k);
                    } else if (k.isReadable()) {
                        received = this.receive(k);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 1500);
            client.start();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}