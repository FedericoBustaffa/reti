import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();

		// Lock
		LockCounter counter = new LockCounter();

		executor = Executors.newCachedThreadPool();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			executor.execute(new Writer(counter));
			executor.execute(new Reader(counter));
		}
		executor.shutdown();
		while (!executor.isTerminated())
			;
		long end = System.currentTimeMillis();
		long counter_time = end - start;

		// RWLock
		ReadWriteLockCounter rwcounter = new ReadWriteLockCounter();

		executor = Executors.newCachedThreadPool();
		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			executor.execute(new Writer(rwcounter));
			executor.execute(new Reader(rwcounter));
		}
		executor.shutdown();
		while (!executor.isTerminated())
			;
		end = System.currentTimeMillis();
		long rwcounter_time = end - start;

		// Risultati
		System.out.printf("Lock Counter: %d ms\n", counter_time);
		System.out.printf("ReadWriteLock Counter: %d ms\n", rwcounter_time);
	}

}