import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Client name: ");
        Client client = new Client(in.nextLine());
        System.out.println("Service on port: " + client.getPort());

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
