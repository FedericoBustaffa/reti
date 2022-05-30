import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	private int counter;

	public Counter(int init_value) {
		this.counter = init_value;
	}

	public int getCounter() {
		return counter;
	}

	public void increment() {
		counter++;
	}
}
