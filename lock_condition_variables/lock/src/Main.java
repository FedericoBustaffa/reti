
public class Main {
    public static void main(String[] args) throws Exception {
        Bufferino bufferino = new Bufferino();

        Consumer consumer = new Consumer(bufferino);
        Producer producer = new Producer(bufferino);

        Thread c = new Thread(consumer);
        Thread p = new Thread(producer);

        p.start();
        c.start();

        c.join();
        p.join();

        System.out.println(bufferino.get());
    }
}
