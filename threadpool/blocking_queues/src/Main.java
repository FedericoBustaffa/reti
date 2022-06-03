import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);
        Consumer consumer = new Consumer(10, queue);
        Producer producer = new Producer(10, queue);

        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);

        consumerThread.start();
        producerThread.start();

        try {
            consumerThread.join();
            producerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println(queue.size());
    }
}
