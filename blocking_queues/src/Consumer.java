import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private int n;
	private BlockingQueue<Integer> queue;

	public Consumer(int n, BlockingQueue<Integer> queue) {
		this.n = n;
		this.queue = queue;
	}

	public void run() {
		int value;
		for (int i = 0; i < this.n; i++) {
			try {
				value = queue.take();
				System.out.println(value + " removed");
			} catch (InterruptedException e) {
				System.out.println("Empty queue");
			}
		}
	}
}
