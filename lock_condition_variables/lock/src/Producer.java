
public class Producer implements Runnable {
	private Buffer b;

	public Producer(Buffer b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			this.b.push();
			System.out.println("push");
		}
	}
}
