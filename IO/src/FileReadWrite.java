import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

public class FileReadWrite {

	private String path;

	public FileReadWrite(String path) {
		this.path = path;
	}

	public void write(String text) {
		try {
			FileOutputStream out = new FileOutputStream(path);
			for (int i = 0; i < text.length(); i++) {
				out.write((int) text.charAt(i));
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Writing error");
		}
	}

	public void read() {
		try {
			FileInputStream in = new FileInputStream(path);
			int c;
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Reading error");
		}
	}

}
