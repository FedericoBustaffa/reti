import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Scanner;

public class Client extends RemoteObject implements Stub {

    private String name;
    private Registry registry;
    private Service service;

    public Client(String name) {
        this.name = name;
        try {
            registry = LocateRegistry.getRegistry(2000);
            service = (Service) registry.lookup("CHAT");
            UnicastRemoteObject.exportObject(this, 0);
            service.registerForCallback(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    public String getName() throws RemoteException {
        return name;
    }

    public String getServerName() throws RemoteException {
        return service.getName();
    }

    public void message(String msg) throws RemoteException {
        service.message(this, msg);
    }

    public void disconnect() throws RemoteException {
        service.unregisterForCallback(this);
        service.disconnectClient(this);
        UnicastRemoteObject.unexportObject(this, true);
    }

    public void sendNotify(String msg) throws RemoteException {
        System.out.println(msg);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome utente: ");
        Client client = new Client(scanner.nextLine());

        try {
            System.out.println("Servizio: " + client.getServerName());
            String s;
            while (!(s = scanner.nextLine()).equals("FINE")) {
                client.message(s);
            }
            client.disconnect();

        } catch (Exception e) {
            System.out.println("Servizio non piu' disponibile");
        }

        scanner.close();
    }
}
