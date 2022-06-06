public class Incrementer implements Runnable {

	private Counter counter;

	public Incrementer(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				synchronized (counter) {
					while (counter.get() >= 20) {
						counter.wait();
					}

					counter.increment();
					System.out.println(counter.get());

					counter.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
