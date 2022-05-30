
public class Producer implements Runnable {

	private Buffer buffer;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try{
			buffer.push();
		} catch (InterruptedException e) {
			
		}
	}
	
}
