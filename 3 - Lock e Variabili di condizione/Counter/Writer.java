
public class Writer implements Runnable {
	
	private LockCounter counter;

	public Writer(LockCounter counter) {
		this.counter = counter;
	}

	public void run() {
		counter.increment();
	}

}
