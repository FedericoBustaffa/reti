
public class Main {
	public static void main(String[] args) throws InterruptedException {

		Dropbox2 dropbox = new Dropbox2();

		Producer producer = new Producer(dropbox);
		Consumer consumer1 = new Consumer(dropbox, true);
		Consumer consumer2 = new Consumer(dropbox, false);

		producer.start();
		consumer1.start();
		consumer2.start();

		producer.join();
		consumer1.join();
		consumer2.join();
	}
}
