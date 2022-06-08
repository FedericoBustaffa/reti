
public class ServerMain {
	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
		} catch (NullPointerException e) {
			System.out.println("Server socket error");
			return;
		}

		System.out.println("Server on port " + server.getPort());
		server.accept();
		server.read();

		server.close();
	}
}
