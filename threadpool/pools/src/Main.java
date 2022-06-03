import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

        for (int i = 0; i < 5; i++) {
            executor.execute(new Consumer(queue));
            executor.execute(new Producer(queue, i));
        }
        executor.shutdown();

        // Potrebbe risvegliarsi con una chiamata spuria
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("ThreadPool size: " + executor.getPoolSize());
        System.out.println("Queue size: " + queue.size());
    }
}
