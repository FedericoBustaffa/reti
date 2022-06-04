import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {

		Buffer buffer = new Buffer(5);

		ExecutorService executor = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 100; i++) {
			executor.execute(new Producer(buffer, i));
			executor.execute(new Consumer(buffer));
		}

		executor.shutdown();

		while (!executor.isTerminated())
			;

		System.out.println(buffer.size());

	}
}