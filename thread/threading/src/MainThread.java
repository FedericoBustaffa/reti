public class MainThread {
    public static void main(String[] args) {
        RunnableInterface runnableInterface = new RunnableInterface();
        Thread t1 = new Thread(runnableInterface);
        ThreadExtension t2 = new ThreadExtension();
        DaemonThread daemonThread = new DaemonThread();
        Thread t3 = new Thread(daemonThread);

        t3.setDaemon(true);
        t3.start();
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t1.interrupt();

        if (t1.isInterrupted()) {
            System.out.println("RunnableInterface interrupted");
        }

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception occurred");
        }

        System.out.printf(
                "T1\nID: %d\nName: %s\nPriority: %d\nStatus: %s\n",
                t1.getId(),
                t1.getName(),
                t1.getPriority(),
                t1.getState());
    }
}
