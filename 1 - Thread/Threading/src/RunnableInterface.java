public class RunnableInterface implements Runnable {
	public void run() {
		System.out.println("Hello from RunnableInterface " + Thread.currentThread().getId());
	}
}
