
public class Producer implements Runnable {
	private Bufferino b;

	public Producer(Bufferino b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			b.push();
			System.out.println("push");
		}
	}
}
