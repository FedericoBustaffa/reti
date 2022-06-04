import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockCounter extends LockCounter {

	private ReadWriteLock rwlock = new ReentrantReadWriteLock();
	private ReadLock read = (ReadLock) rwlock.readLock();
	private WriteLock write = (WriteLock) rwlock.writeLock();

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
