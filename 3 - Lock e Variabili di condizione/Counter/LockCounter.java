import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter extends Counter {
	
	private Lock lock = new ReentrantLock();

	public void increment() {
		lock.lock();
		super.increment();
		lock.unlock();
	}
	
	public void get() {
		lock.lock();
		super.get();
		lock.unlock();
	}

}
