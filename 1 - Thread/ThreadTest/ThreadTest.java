package ThreadTest;

public class ThreadTest implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello World from Thread " + Thread.currentThread().getId());
	}

}
