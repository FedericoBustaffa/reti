import java.util.concurrent.Callable;

public class Power implements Callable<Double> {
	private double n;

	public Power(double n) {
		this.n = n;
	}

	public Double call() {
		return Math.pow(this.n, 2);
	}
}
