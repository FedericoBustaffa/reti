import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	private int buffer;
	private Lock lock;

	public Buffer() {
		this.buffer = 0;
		this.lock = new ReentrantLock();
	}

	public void push() {
		this.lock.lock();
		try {
			this.buffer++;
		} finally {
			this.lock.unlock();
		}
	}

	public void pop() {
		this.lock.lock();
		try {
			this.buffer--;
		} finally {
			this.lock.unlock();
		}
	}

	public int get() {
		return this.buffer;
	}
}
