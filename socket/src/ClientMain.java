import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        System.out.println("Server on port " + client.getPort());

        Scanner in = new Scanner(System.in);
        client.write(in.nextLine());

        in.close();
        client.close();
    }
}
