public class Task implements Runnable {

	private Buffer buffer;
	private int x;

	public Task(Buffer buffer, int x) {
		this.buffer = buffer;
		this.x = x;
	}

	public void run() {
		this.buffer.push(this.x);
	}
}
