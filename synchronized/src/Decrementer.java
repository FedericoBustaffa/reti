public class Decrementer implements Runnable {

	private Counter counter;

	public Decrementer(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		try {
			counter.decrement();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
