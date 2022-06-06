public class Incrementer implements Runnable {

	private Counter counter;

	public Incrementer(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		try {
			counter.increment();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
