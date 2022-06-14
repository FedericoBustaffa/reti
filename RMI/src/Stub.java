import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stub extends Remote {

	public String getName() throws RemoteException;

	public void sendNotify(String msg) throws RemoteException;

}
