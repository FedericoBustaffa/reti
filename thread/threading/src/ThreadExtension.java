public class ThreadExtension extends Thread {
	public void run() {
		System.out.println("Hello from ThreadExtension " + Thread.currentThread().getId());
	}
}
