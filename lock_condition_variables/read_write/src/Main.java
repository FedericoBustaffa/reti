import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        Buffer buffer = new Buffer();

        ExecutorService pool = Executors.newCachedThreadPool();

        Thread writer = new Thread(new Writer(buffer));
        writer.start();
        for (int i = 0; i < 100; i++) {
            pool.execute(new Reader(buffer));
        }

        writer.join();
        pool.shutdown();
    }
}
