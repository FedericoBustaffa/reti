import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

	public String getName() throws RemoteException;

	public void message(Stub client, String s) throws RemoteException;

	public void registerForCallback(Stub client) throws RemoteException;

	public void unregisterForCallback(Stub client) throws RemoteException;

	public void disconnectClient(Stub client) throws RemoteException;

}
