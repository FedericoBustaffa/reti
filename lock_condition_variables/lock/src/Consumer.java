
public class Consumer implements Runnable {
	private Bufferino b;

	public Consumer(Bufferino b) {
		this.b = b;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			if (this.b.get() == 0) {
				System.out.println("empty buffer");
			} else {
				this.b.pop();
				System.out.println("pop");
			}
		}
	}
}
