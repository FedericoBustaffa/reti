import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter extends LockCounter {
	
	private ReadWriteLock rwlock = new ReentrantReadWriteLock();
	private Lock read = rwlock.readLock();
	private Lock write = rwlock.writeLock();

	public void increment() {
		read.lock();
		super.increment();
		read.unlock();
	}

	public void get() {
		write.lock();
		super.get();
		write.unlock();
	}

}
