import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.execute(new Incrementer(counter));
        }

        Thread.sleep(1000);
        System.out.println("Counter: " + counter.get());

        for (int i = 0; i < 100; i++) {
            pool.execute(new Decrementer(counter));
        }

        pool.shutdown();
        while (!pool.isTerminated())
            ;

        System.out.println("Counter: " + counter.get());
    }
}
