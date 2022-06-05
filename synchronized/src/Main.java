import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.execute(new Task(buffer, i));
        }
        pool.shutdown();
        while (!pool.isTerminated())
            ;

        buffer.display();
    }
}
