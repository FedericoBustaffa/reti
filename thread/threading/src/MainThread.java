
public class MainThread {
    public static void main(String[] args) {
        // Interface
        RunnableInterface runnableInterface = new RunnableInterface();
        Thread t1 = new Thread(runnableInterface);

        // extension
        ThreadExtension t2 = new ThreadExtension();

        // daemon
        DaemonThread daemonThread = new DaemonThread();
        Thread t3 = new Thread(daemonThread);

        t1.setPriority(Thread.MAX_PRIORITY);
        t3.setDaemon(true);

        t1.start();
        t2.start();
        t3.start();

        // interrupt t1
        t1.interrupt();
        if (t1.isInterrupted()) {
            System.out.println("RunnableInterface interrupted");
        }

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception occurred");
        }

        // t1 settings
        System.out.printf(
                "T1 settings\nID: %d\nName: %s\nPriority: %d\nStatus: %s\n",
                t1.getId(),
                t1.getName(),
                t1.getPriority(),
                t1.getState());
    }
}
