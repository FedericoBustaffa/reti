
public class Reader implements Runnable {
	
	private LockCounter counter;

	public Reader(LockCounter counter) {
		this.counter = counter;
	}

	public void run() {
		counter.get();
	}

}
