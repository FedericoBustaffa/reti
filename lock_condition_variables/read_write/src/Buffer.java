import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Buffer {
	private Vector<Integer> buffer;

	private Lock mutex;
	// private ReadWriteLock rw_mutex;
	// private ReadLock r_mutex;
	// private WriteLock w_mutex;

	public Buffer() {
		this.buffer = new Vector<Integer>();

		this.mutex = new ReentrantLock();
	}

	public void push() {
		mutex.lock();
		buffer.add(1);
		mutex.unlock();
	}

	public void size() {
		mutex.lock();
		System.out.println("Size: " + buffer.size());
		mutex.unlock();
	}
}
