public class Viewer extends Thread {

	public volatile boolean running;

	public Viewer() {
		this.running = true;
	}

	public void run() {
		while (running) {
			System.out.println("Running");
		}
	}
}
