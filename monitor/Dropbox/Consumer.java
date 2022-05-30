
public class Consumer extends Thread {

	private Dropbox dropbox;
	private boolean value;

	public Consumer(Dropbox dropbox, boolean value) {
		this.dropbox = dropbox;
		this.value = value;
	}

	public void run() {
		dropbox.take(value);
	}

}
