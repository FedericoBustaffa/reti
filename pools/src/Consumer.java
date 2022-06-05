import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<Integer> queue;

	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			System.out.println(this.queue.take() + " out");
		} catch (InterruptedException e) {
			System.out.println("Consumer interrupted");
		}
	}
}
