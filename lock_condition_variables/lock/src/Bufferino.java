import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bufferino {
	private int buffer;
	private Lock lock;

	public Bufferino() {
		this.buffer = 0;
		this.lock = new ReentrantLock();
	}

	public void push() {
		this.lock.lock();
		this.buffer++;
		this.lock.unlock();
	}

	public void pop() {
		this.lock.lock();
		this.buffer--;
		this.lock.unlock();
	}

	public int get() {
		return this.buffer;
	}
}
