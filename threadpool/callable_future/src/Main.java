import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Power power = new Power(4.0);
        Future<Double> result = executor.submit(power);
        executor.shutdown();
        System.out.println(result.get());
    }
}
