import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {

		Buffer buffer = new Buffer();

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			executor.execute(new Producer(buffer));
			//executor.execute(new Consumer(buffer));
		}

		executor.shutdown();

		if (executor.isTerminated()) {
			System.out.println(buffer.status());
		}

	}
}