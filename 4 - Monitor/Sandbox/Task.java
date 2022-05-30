import java.util.List;

public class Task extends Thread {

	private List<Integer> v;

	public Task(List<Integer> v) {
		this.v = v;
	}

	public void run() {
		v.add(1);
	}

}
