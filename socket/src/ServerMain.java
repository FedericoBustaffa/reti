import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Server size: ");
		Server server = new Server("Chat", in.nextInt());
		System.out.println("Server on port " + server.getPort());

		server.start();
		server.shutdown();

		in.close();
	}
}
