import java.rmi.registry.*;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2000);
            Service service = (Service) registry.lookup("SERVICE");
            System.out.println(service.hello());
            System.out.println(service.echo("CIAO"));
            System.out.println(service.date());
            service.shutdown();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}
