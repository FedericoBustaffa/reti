import java.io.File;

public class Producer implements Runnable {

	/**
	 * Lista delle directories incontrate nella visita
	 */
	private MyLinkedList directories;

	public Producer(MyLinkedList directories) {
		this.directories = directories;
	}

	/**
	 * visito ricorsivamente le cartelle. Una volta entrato in una cartella ne metto
	 * il contenuto in un array se si tratta di una sottocartella avvio una visita
	 * ricorsiva sulla cartella e ne metto il path assoluto nella lista
	 * "directories"
	 * 
	 * @param dir
	 */
	private void visit(File dir) {
		File[] dir_content = dir.listFiles();
		for (File f : dir_content) {
			if (f != null && f.isDirectory()) {
				directories.add(f.getAbsolutePath());
				this.visit(f);
			}
		}
	}

	/**
	 * avvio una visita ricorsiva delle cartelle controllando che il path di
	 * partenza sia valido
	 */
	public void run() {
		File file = new File(directories.get(0));
		if (file.exists() && file.isDirectory()) {
			this.visit(file);
		} else {
			System.out.println("Invalid Path: " + directories.get(0));
		}
		directories.finish();
	}

}
