import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private BlockingQueue<Integer> queue;
	private int n;

	public Producer(BlockingQueue<Integer> queue, int n) {
		this.queue = queue;
		this.n = n;
	}

	public void run() {
		try {
			this.queue.put(this.n);
			System.out.println(this.n + " in");
		} catch (InterruptedException e) {
			System.out.println("Producer interrupted");
		}
	}
}
