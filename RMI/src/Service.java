import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

	public String hello() throws RemoteException;

	public String echo(String s) throws RemoteException;

	public String date() throws RemoteException;

	public void shutdown() throws RemoteException;

}
