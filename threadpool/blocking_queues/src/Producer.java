import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private int n;
	private BlockingQueue<Integer> queue;

	public Producer(int n, BlockingQueue<Integer> queue) {
		this.n = n;
		this.queue = queue;
	}

	public void run() {
		for (int i = 0; i < this.n; i++) {
			try {
				queue.put(i);
				System.out.println(i + " inserted");
			} catch (InterruptedException e) {
				System.out.println("Can't add " + i + ": full queue");
			}
		}
	}
}
