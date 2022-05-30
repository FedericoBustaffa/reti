public class MainThread {
    public static void main(String[] args) throws Exception {
        ThreadTest threadTest = new ThreadTest();
        Thread t = new Thread(threadTest);

        t.start();
        t.join();
    }
}
