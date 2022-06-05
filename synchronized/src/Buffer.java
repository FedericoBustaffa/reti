import java.util.Vector;

public class Buffer {

	private Vector<Integer> buffer;

	public Buffer() {
		buffer = new Vector<Integer>();
	}

	public synchronized void push(int x) {
		buffer.add(x);
	}

	public void display() {
		for (Integer i : buffer) {
			System.out.println(i);
		}
	}

}
