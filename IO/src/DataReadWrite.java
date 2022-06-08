import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

public class DataReadWrite {

	private String path;

	public DataReadWrite(String path) {
		this.path = path;
	}

	public int write(String text) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
			out.writeChars(text);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Writing error");
		}

		return text.length();
	}

	public void read(int length) {
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
			for (int i = 0; i < length; i++) {
				System.out.print(in.readChar());
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
