import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Sender {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private int local_port;
    private InetSocketAddress sock_addr;
    private int buffer_size;

    public Sender(String name, InetSocketAddress sock_addr) {
        try {
            socket = new DatagramSocket(0);
            local_port = socket.getLocalPort();
            buffer_size = socket.getSendBufferSize();

            byte[] buf = name.getBytes("UTF-8");
            packet = new DatagramPacket(buf, 0, buf.length, sock_addr);
            socket.send(packet);
            this.sock_addr = sock_addr;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding");
        } catch (SocketException e) {
            System.out.println("Errore socket");
        } catch (IOException e) {
            System.out.println("Errore nell'invio del messaggio");
        }
    }

    public int getLocalPort() {
        return local_port;
    }

    public String getServerAddress() {
        return sock_addr.getAddress().getHostAddress();
    }

    public int getPort() {
        return sock_addr.getPort();
    }

    public int getBufferSize() {
        return buffer_size;
    }

    public void send(String msg) {
        try {
            packet = new DatagramPacket(msg.getBytes("UTF-8"), 0, msg.length(), sock_addr);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Errore nell'invio del messaggio");
        }
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        InetSocketAddress sock_addr = new InetSocketAddress(InetAddress.getLocalHost(), 1500);
        Sender sender = new Sender("Federico", sock_addr);
        if (sender.getLocalPort() != -1) {
            System.out.println("Sono connesso alla porta " + sender.getLocalPort());
            System.out.println("Dimensione send_buffer: " + sender.getBufferSize() / 1024 + " KB");
        } else {
            System.out.println("Avvio sender fallito");
            return;
        }

        if (sender.getPort() != -1) {
            System.out.printf("Server: %s\nPorta: %d\n", sender.getServerAddress(), sender.getPort());
        }
        Scanner input = new Scanner(System.in);
        String msg;
        do {
            System.out.print("Messaggio: ");
            msg = input.nextLine();
            sender.send(msg);
        } while (!msg.equals("FINE"));

        input.close();
        sender.close();
    }
}
