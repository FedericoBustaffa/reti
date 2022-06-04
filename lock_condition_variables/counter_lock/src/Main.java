import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {

		LockCounter counter = new LockCounter();
		ReadWriteLockCounter rwcounter = new ReadWriteLockCounter();

		long start = System.currentTimeMillis();
		ExecutorService executor1 = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor1.execute(new Writer(counter));
			executor1.execute(new Reader(counter));
		}
		executor1.shutdown();
		while (!executor1.isTerminated())
			;

		long end = System.currentTimeMillis();
		long time1 = end - start;

		start = System.currentTimeMillis();
		ExecutorService executor2 = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executor2.execute(new Writer(rwcounter));
			executor2.execute(new Reader(rwcounter));
		}
		executor2.shutdown();
		while (!executor2.isTerminated())
			;
		end = System.currentTimeMillis();
		long time2 = end - start;

		System.out.printf("Time1: %d ms\n", time1);
		System.out.printf("Time2: %d ms\n", time2);

	}

}