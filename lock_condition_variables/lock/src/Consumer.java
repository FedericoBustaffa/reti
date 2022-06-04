
public class Consumer implements Runnable {
	private Buffer b;

	public Consumer(Buffer b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			this.b.pop();
			System.out.println("pop");
		}
	}
}
