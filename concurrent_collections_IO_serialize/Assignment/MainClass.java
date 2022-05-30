
public class MainClass {
	public static void main(String[] args) {

		int k = Integer.parseInt(args[0]); // numero di thread consumatori
		String path = args[1]; // path di partenza

		MyLinkedList directories = new MyLinkedList();
		directories.add(path);

		Thread producer = new Thread(new Producer(directories));
		producer.start();

		// creazione e avvio thread
		Thread[] consumers = new Thread[k];
		for (int i = 0; i < k; i++) {
			consumers[i] = new Thread(new Consumer(directories));
			consumers[i].start();
		}

		// Chiusura thread
		try {
			producer.join();
		} catch (InterruptedException e) {
			System.out.println(e.getStackTrace());
		}

		for (int i = 0; i < k; i++) {
			try {
				consumers[i].join();
			} catch (InterruptedException e) {
				System.out.println(e.getStackTrace());
			}
		}
	}
}