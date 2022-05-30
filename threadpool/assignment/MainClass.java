import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Vector;

public class MainClass {
	public static void main(String[] args) {
		
		int n = 50; // numero totale di persone
		int k = 10; // lunghezza della coda nel threadpool
		ExecutorService service = new ThreadPoolExecutor(
			4,
			4,
			120,
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(k)
		);

		Vector<Person> room = new Vector<Person>(); // prima sala di attesa
		for (int i = 0; i < n; i++) {
			room.add(new Person(i));
		}

		int i = 0;
		while (i != room.size()) {
			try {
				// provo costantemente a inserire i task presenti nella coda della prima sala
				// se il threadpool è pieno non faccio niente e continuo a ciclare
				// il ciclo termina quando esaurisco la coda 
				service.execute(room.get(i));
				i++;
			} catch (RejectedExecutionException e) { }
		}

		service.shutdown();
	}
}