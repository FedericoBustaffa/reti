import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Date;

public class Server extends RemoteServer implements Service {

	private Service service;
	private Registry registry;
	private String name;

	public Server(int port, String name) throws RemoteException {
		this.name = name;
		service = (Service) UnicastRemoteObject.exportObject(this, 0);
		LocateRegistry.createRegistry(port);
		registry = LocateRegistry.getRegistry(port);
	}

	public void run() {
		try {
			registry.rebind(name, this.service);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		try {
			UnicastRemoteObject.unexportObject(this, true);
			registry.unbind(name);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	public String hello() {
		return "HELLO";
	}

	public String echo(String s) {
		return "echo: " + s;
	}

	public String date() {
		return new Date().toString();
	}

	public static void main(String[] args) {
		try {
			Server server = new Server(2000, "SERVICE");
			server.run();
		} catch (RemoteException e) {
			System.out.println("Errore di comunicazione " + e.toString());
		}
	}
}
