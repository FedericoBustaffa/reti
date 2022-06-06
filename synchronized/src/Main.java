import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Decrementer(counter));
        pool.execute(new Incrementer(counter));

        pool.shutdown();
        while (!pool.isTerminated())
            ;

        System.out.println("Counter: " + counter.get());
    }
}
