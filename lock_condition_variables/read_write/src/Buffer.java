import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Buffer {

	private int a, b;
	private ReadWriteLock rw_mutex;
	private Lock r_mutex;
	private Lock w_mutex;

	public Buffer() {
		this.a = 1000;
		this.b = 0;
		this.rw_mutex = new ReentrantReadWriteLock();
		this.r_mutex = rw_mutex.readLock();
		this.w_mutex = rw_mutex.writeLock();
	}

	public int sum() {
		int result;
		r_mutex.lock();
		result = a + b;
		r_mutex.unlock();

		return result;
	}

	public void transfer(int x) {
		w_mutex.lock();
		a = a - x;
		b = b + x;
		w_mutex.unlock();
	}
}
