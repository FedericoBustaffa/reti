
public class Producer implements Runnable {

	private Buffer buffer;
	private int n;

	public Producer(Buffer buffer, int n) {
		this.buffer = buffer;
		this.n = n;
	}

	public void run() {
		try {
			buffer.push(this.n);
		} catch (InterruptedException e) {

		}
	}

}
