import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {

	private Dropbox dropbox;

	public Producer(Dropbox dropbox) {
		this.dropbox = dropbox;
	}

	public void run() {
		int value = ThreadLocalRandom.current().nextInt(100);
		dropbox.put(value);
	}

}
