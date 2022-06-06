import java.io.Serializable;

public class Value implements Serializable {

	private static final long serialVersionUID = 1;
	private int value;

	public Value() {
		this.value = 0;
	}

	public void set(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}

}
