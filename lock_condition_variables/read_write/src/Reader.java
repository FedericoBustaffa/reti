public class Reader implements Runnable {
	private Buffer buffer;

	public Reader(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		buffer.size();
	}
}
