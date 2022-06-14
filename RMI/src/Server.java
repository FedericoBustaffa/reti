import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Vector;

public class Server extends RemoteServer implements Service {

	private Service service;
	private Registry registry;
	private String name;
	private Vector<Stub> clients;

	public Server(int port, String name) throws RemoteException {
		this.name = name;
		this.clients = new Vector<Stub>();
		service = (Service) UnicastRemoteObject.exportObject(this, 0);
		LocateRegistry.createRegistry(port);
		registry = LocateRegistry.getRegistry(port);
	}

	public String getName() throws RemoteException {
		return name;
	}

	public void run() {
		try {
			registry.rebind(name, this.service);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void message(Stub client, String msg) throws RemoteException {
		for (Stub c : clients) {
			try {
				if (!c.equals(client))
					c.sendNotify(client.getName() + ": " + msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void registerForCallback(Stub client) throws RemoteException {
		for (Stub c : clients) {
			c.sendNotify(client.getName() + " si e' connesso");
		}

		if (!clients.contains(client)) {
			clients.add(client);
			System.out.println(client.getName() + " si e' connesso");
		}
	}

	public synchronized void unregisterForCallback(Stub client) throws RemoteException {
		for (Stub c : clients) {
			if (!c.equals(client))
				c.sendNotify(client.getName() + " si e' disconnesso");
		}

		if (clients.remove(client)) {
			System.out.println(client.getName() + " si e' disconnesso");
		}
	}

	public void disconnectClient(Stub client) {
		try {
			clients.remove(client);
			if (clients.isEmpty()) {
				registry.unbind(name);
				UnicastRemoteObject.unexportObject(this, true);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			Server server = new Server(2000, "CHAT");
			server.run();
		} catch (RemoteException e) {
			System.out.println("Errore di comunicazione " + e.toString());
		}
	}

}
