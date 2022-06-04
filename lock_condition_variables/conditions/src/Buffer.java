import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.Vector;

public class Buffer {

	private final int buffer_size;
	private Vector<Integer> buffer;
	private Lock mutex;
	private Condition empty;
	private Condition full;

	public Buffer(int buffer_size) {
		this.buffer_size = buffer_size;
		buffer = new Vector<Integer>(this.buffer_size);

		mutex = new ReentrantLock();
		empty = mutex.newCondition();
		full = mutex.newCondition();
	}

	public int size() {
		return buffer.size();
	}

	public boolean isEmpty() {
		return buffer.isEmpty();
	}

	public boolean isFull() {
		return buffer.size() == buffer_size;
	}

	public void push(int n) throws InterruptedException {
		mutex.lock();
		while (buffer.size() == buffer_size) {
			empty.await();
		}

		buffer.add(n);
		System.out.print(n + " added ");
		System.out.println("buffer size: " + this.size());

		full.signal();
		mutex.unlock();
	}

	public void pop() throws InterruptedException {
		mutex.lock();
		while (buffer.isEmpty()) {
			full.await();
		}

		System.out.print(buffer.remove(0) + " removed ");
		System.out.println("buffer size: " + this.size());

		empty.signal();
		mutex.unlock();
	}

}
