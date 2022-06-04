
public class Main {
	public static void main(String[] args) throws InterruptedException {

		Thread[] readers = new Thread[1000];
		Thread[] writers = new Thread[1000];

		// Lock Counter
		LockCounter counter = new LockCounter();

		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			readers[i] = new Thread(new Reader(counter));
			writers[i] = new Thread(new Writer(counter));

			readers[i].start();
			writers[i].start();
		}

		for (int i = 0; i < 1000; i++) {
			readers[i].join();
			writers[i].join();
		}

		long end = System.currentTimeMillis();
		long counter_time = end - start;

		// Read Write Lock counter
		ReadWriteLockCounter rwcounter = new ReadWriteLockCounter();

		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			readers[i] = new Thread(new Reader(rwcounter));
			writers[i] = new Thread(new Writer(rwcounter));

			readers[i].start();
			writers[i].start();
		}

		for (int i = 0; i < 1000; i++) {
			readers[i].join();
			writers[i].join();
		}
		end = System.currentTimeMillis();
		long rwcounter_time = end - start;

		System.out.printf("Lock Counter: %d ms\n", counter_time);
		System.out.printf("ReadWriteLock Counter: %d ms\n", rwcounter_time);
	}

}