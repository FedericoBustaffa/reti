import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        Buffer buffer = new Buffer();
        ExecutorService pool = Executors.newFixedThreadPool(100);

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pool.execute(new Reader(buffer));
            pool.execute(new Writer(buffer));
        }
        pool.shutdown();

        while (!pool.isTerminated())
            ;
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
}
