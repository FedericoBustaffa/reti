public class Decrementer implements Runnable {

	private Counter counter;

	public Decrementer(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				synchronized (counter) {
					while (counter.get() <= 0) {
						counter.wait();
					}

					counter.decrement();
					System.out.println(counter.get());

					counter.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
