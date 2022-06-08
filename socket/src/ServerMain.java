import java.util.Scanner;

public class ServerMain {
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
