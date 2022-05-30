import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		List<Integer> synch_v = Collections.synchronizedList(new ArrayList<Integer>());

		Task[] tasks = new Task[1000];
		for (int i = 0; i < 1000; i++) {
			tasks[i] = new Task(synch_v);
		}

		for (int i = 0; i < 1000; i++) {
			tasks[i].start();
		}

		for (int i = 0; i < 1000; i++) {
			tasks[i].join();
		}

		System.out.println(synch_v.size());
	}
}