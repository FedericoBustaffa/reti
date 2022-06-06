
public class Main {
    public static void main(String[] args) throws Exception {
        Viewer viewer = new Viewer();
        viewer.start();

        Thread.sleep(2000);
        viewer.running = false;

        viewer.join();
    }
}
