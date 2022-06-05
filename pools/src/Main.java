import java.util.concurrent.TimeUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                0,
                10,
                0L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

        for (int i = 0; i < 30; i++) {
            executor.execute(new Consumer(queue));
            executor.execute(new Producer(queue, i));
        }
        executor.shutdown();

        // Potrebbe risvegliarsi con una chiamata spuria
        while (!executor.isTerminated())
            ;

        System.out.println("ThreadPool size: " + executor.getPoolSize());
        System.out.println("Queue size: " + queue.size());
    }
}
