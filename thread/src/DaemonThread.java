
public class DaemonThread implements Runnable {

	public void run() {
		while (true) {
			System.out.println("Daemon Thread " + Thread.currentThread().getId());
		}
	}

}
