import java.io.File;

public class Consumer implements Runnable {

	private MyLinkedList directories;

	public Consumer(MyLinkedList directories) {
		this.directories = directories;
	}

	/**
	 * Ogni consumatore rimane attivo finché la lista non è vuota o finché la visita
	 * del produttore non è terminata. Ogni consumatore prende una directory e
	 * stampa tutti i file al suo interno.
	 */
	public void run() {
		while (!directories.isEmpty() || !directories.isFinished()) {
			String path = directories.pop();
			if (path != null) {
				File file = new File(path);
				if (file.exists() && file.isDirectory()) {
					File[] files = file.listFiles();
					for (File f : files) {
						if (f != null && f.isFile()) {
							System.out.println(f.getAbsolutePath());
						}
					}
				}
			}
		}
	}

}
