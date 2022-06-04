public class Writer implements Runnable {

	private Buffer buffer;

	public Writer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			buffer.transfer(i);
		}
	}
}
