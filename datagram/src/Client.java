import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private DatagramSocket socket;
    private DatagramPacket packet;
    private int port;

    public Client(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Errore socket");
        }
    }

    public int getPort() {
        return port;
    }

    public void send(String msg, InetAddress host) {
        try {
            packet = new DatagramPacket(msg.getBytes("US-ASCII"), msg.length(), host, port);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Errore invio messaggio");
        }
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        Client client = new Client(1500);
        System.out.println("Invio messaggio sulla porta " + client.getPort());
        InetAddress host = InetAddress.getLocalHost();

        Scanner input = new Scanner(System.in);
        String msg;
        do {
            msg = input.nextLine();
            client.send(msg, host);
        } while (!msg.equals("FINE"));

        input.close();
        client.close();
    }
}
