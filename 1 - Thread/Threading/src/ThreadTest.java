public class ThreadTest implements Runnable {
	public void run() {
		System.out.println("Hello World from " + Thread.currentThread().getId());
	}
}
