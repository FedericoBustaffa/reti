import java.util.concurrent.Callable;

public class Fibonacci implements Callable<Integer> {
	
	private int n;

	public Fibonacci(int n) {
		this.n = n;
	}

	private static int fibonacci(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public Integer call() {
		return fibonacci(n);
	}
}
