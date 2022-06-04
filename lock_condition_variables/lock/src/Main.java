
public class Main {
    public static void main(String[] args) throws Exception {
        Buffer bufferino = new Buffer();

        Consumer consumer = new Consumer(bufferino);
        Producer producer = new Producer(bufferino);

        Thread c = new Thread(consumer);
        Thread p = new Thread(producer);

        long time1 = System.currentTimeMillis();
        c.start();
        p.start();

        c.join();
        p.join();
        long time2 = System.currentTimeMillis();
        System.out.println("Execution time: " + (double) (time2 - time1) / 1000 + "s");

        System.out.println(bufferino.get());
    }
}
