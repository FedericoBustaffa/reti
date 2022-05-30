import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newFixedThreadPool(5);

		Vector<Future<Integer>> results = new Vector<Future<Integer>>();
		
		for (int i = 0; i < 5; i++) {
			results.add(executor.submit(new Fibonacci(45)));
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(results.get(i).get());
		}
		executor.shutdown();

	}
}