import java.io.File;
import java.util.LinkedList;

public class Main {

	public static LinkedList<String> files = new LinkedList<String>();
	public static LinkedList<String> directories = new LinkedList<String>();

	public static void explore(File file) {

		File[] dir_content = file.listFiles();
		for (File f : dir_content) {
			if (f.isDirectory()) {
				directories.add(f.getName());
				explore(f);
			}

			if (f.isFile()) {
				files.add(f.getName());
			}
		}

	}

	public static void main(String[] args) {
		explore(new File("."));

		System.out.println("Files:");
		for (String f : files) {
			System.out.println(f);
		}

		System.out.println("Directories:");
		for (String d : directories) {
			System.out.println(d);
		}
	}
}