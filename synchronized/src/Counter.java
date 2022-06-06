
public class Counter {

	private int counter;

	public Counter() {
		counter = 0;
	}

	public void decrement() throws InterruptedException {
		synchronized (this) {
			while (counter <= 0) {
				wait();
			}

			counter--;

			notify();
		}
	}

	public void increment() throws InterruptedException {
		synchronized (this) {
			while (counter >= 20) {
				wait();
			}

			counter++;

			notify();
		}
	}

	public int get() {
		return counter;
	}

}
