public class Consumer implements Runnable {
	
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try{
			buffer.pop();
		} catch (InterruptedException e) {
			
		}
	}

}
