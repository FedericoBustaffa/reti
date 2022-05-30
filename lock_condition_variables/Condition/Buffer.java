import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buffer {

	private boolean buffer;
	private Lock mutex;
	private Condition empty;
	private Condition full;

	public Buffer() {
		buffer = false;
		mutex = new ReentrantLock();
		empty = mutex.newCondition();
		full = mutex.newCondition();
	}

	public boolean status() {
		return buffer;
	}

	public boolean isEmpty() {
		return buffer == false;
	}

	public boolean isFull() {
		return buffer == true;
	}

	public void push() throws InterruptedException {
		mutex.lock();
		while (buffer == true) {
			empty.await();
		}

		buffer = true;
		
		full.signal();
		mutex.unlock();
	}

	public void pop() throws InterruptedException {
		mutex.lock();
		while (buffer == false) {
			full.await();
		}

		buffer = false;

		empty.signal();
		mutex.unlock();
	}

}
