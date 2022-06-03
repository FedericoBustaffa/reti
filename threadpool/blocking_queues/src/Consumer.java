import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private int n;
	private BlockingQueue<Integer> queue;

	public Consumer(int n, BlockingQueue<Integer> queue) {
		this.n = n;
		this.queue = queue;
	}

	public void run() {
		for (int i = 0; i < this.n; i++) {
			try {
				System.out.println(queue.take() + " removed");
			} catch (InterruptedException e) {
				System.out.println("Consumer interrupted");
			}
		}
	}
}
